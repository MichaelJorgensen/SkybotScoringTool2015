package com.nwctarobotics.SkybotScoringSoftware;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class MatchHandler {

    private Main main;
    private HashMap<Integer, Match> matches = new HashMap<Integer, Match>();

    private DefaultTableModel d = new DefaultTableModel(0, 7);
    private String[] columns = new String[] { "Match #", "Blue 1", "Blue 2", "Red 1", "Red 2", "Blue Score", "Red Score" };

    public MatchHandler(Main main, JTable table) {
        this.main = main;
        d.setColumnIdentifiers(columns);
        table.setModel(d);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        refreshMatches(false);
    }

    public void refreshMatches(boolean updateRanks) {
        Main.send("Retrieving match information");
        HashMap<Integer, Match> newMatches = new HashMap<Integer, Match>();
        int partialMatches = 0;
        try {
            ResultSet rs = main.getSQL().query("SELECT * FROM skybotMatches");
            while (rs.next()) {
                int id = rs.getInt("matchID");
                String blue1 = rs.getString("blue1");
                String blue2 = rs.getString("blue2");
                String red1 = rs.getString("red1");
                String red2 = rs.getString("red2");
                int blueScore = rs.getInt("blueScore");
                int redScore = rs.getInt("redScore");
                newMatches.put(id, new Match(id, new Team(blue1), new Team(blue2), new Team(red1), new Team(red2), blueScore, redScore));
                if (blueScore == -1 || redScore == -1) {
                    partialMatches++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Main.send("Updating match table");
        if (newMatches.isEmpty()) {
            matches.clear();
            d.setRowCount(0);
            main.setProgressBar(0, 0);
            return;
        }
        main.setProgressBar(newMatches.size() - partialMatches, newMatches.size());
        d.setRowCount(0);
        for (Entry<Integer, Match> en : newMatches.entrySet()) {
            if (en.getValue().getBlueScore() == -1 || en.getValue().getRedScore() == -1) {
                d.addRow(new Object[] { en.getKey(), en.getValue().getBlue1().getName(), en.getValue().getBlue2().getName(), en.getValue().getRed1().getName(), en.getValue().getRed2().getName(), "", "" });
            } else {
                d.addRow(new Object[] { en.getKey(), en.getValue().getBlue1().getName(), en.getValue().getBlue2().getName(), en.getValue().getRed1().getName(), en.getValue().getRed2().getName(), en.getValue().getBlueScore(), en.getValue().getRedScore() });
            }
        }
        matches.clear();
        matches.putAll(newMatches);
        if (updateRanks)
            main.getTeamHandler().refreshResults();
    }

    public boolean recordMatch(int id, String blue1, String blue2, String red1, String red2, int blueScore, int redScore) {
        refreshMatches(false);
        if (!matches.containsKey(id)) {
            try {
                main.getSQL().query("INSERT INTO skybotMatches (matchID, blue1, blue2, red1, red2, blueScore, redScore) VALUES (" + id + ", '" + blue1 + "', '" + blue2 + "', '" + red1 + "', '" + red2 + "', " + blueScore + ", " + redScore + ")");
                refreshMatches(true);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                Main.error("Error setting match, error message: " + e.getMessage());
            }
        } else {
            Main.error("Match already exists, either MODIFY the match or DELETE the match and try again");
        }
        return false;
    }

    public boolean recordPartialMatch(int id, String blue1, String blue2, String red1, String red2) {
        refreshMatches(false);
        if (!matches.containsKey(id)) {
            try {
                main.getSQL().query("INSERT INTO skybotMatches (matchID, blue1, blue2, red1, red2, blueScore, redScore) VALUES (" + id + ", '" + blue1 + "', '" + blue2 + "', '" + red1 + "', '" + red2 + "', " + -1 + ", " + -1 + ")");
                refreshMatches(false);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                Main.error("Error setting match, error message: " + e.getMessage());
            }
        } else {
            Main.error("Match already exists, either MODIFY the match or DELETE the match and try again");
        }
        return false;
    }

    public boolean modifyMatch(int id, String blue1, String blue2, String red1, String red2, int blueScore, int redScore) {
        refreshMatches(false);
        if (matches.containsKey(id)) {
            try {
                main.getSQL().query("UPDATE skybotMatches SET blue1='" + blue1 + "', blue2='" + blue2 + "', red1='" + red1 + "', red2='" + red2 + "', blueScore=" + blueScore + ", redScore=" + redScore + " WHERE matchID=" + id);
                refreshMatches(true);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                Main.error("Database error modifying match, message: " + e.getMessage());
            }
        } else {
            Main.error("Match doesn't exist");
        }
        return false;
    }

    public boolean updatePartialMatch(int id, int blueScore, int redScore) {
        refreshMatches(false);
        if (matches.containsKey(id)) {
            try {
                main.getSQL().query("UPDATE skybotMatches SET blueScore=" + blueScore + ", redScore=" + redScore + " WHERE matchID=" + id);
                refreshMatches(true);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                Main.error("Database error modifying match, message: " + e.getMessage());
            }
        } else {
            Main.error("Match doesn't exist");
        }
        return false;
    }

    public boolean deleteMatch(int id) {
        refreshMatches(false);
        if (matches.containsKey(id)) {
            try {
                main.getSQL().query("DELETE FROM skybotMatches WHERE matchID=" + id);
                refreshMatches(true);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                Main.error("Error deleting match, error message: " + e.getMessage());
            }
        } else {
            Main.error("Match does not exist");
        }
        return false;
    }

    public HashMap<Integer, Match> getMatches() {
        return matches;
    }
}
