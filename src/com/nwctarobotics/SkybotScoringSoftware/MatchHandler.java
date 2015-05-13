package com.nwctarobotics.SkybotScoringSoftware;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MatchHandler {

    private Main main;
    private HashMap<Integer, Match> matches = new HashMap<Integer, Match>();

    DefaultTableModel d = new DefaultTableModel(0, 7);
    String[] columns = new String[] { "Match #", "Blue 1", "Blue 2", "Red 1", "Red 2", "Blue Score", "Red Score" };

    public MatchHandler(Main main, JTable table) {
        this.main = main;
        d.setColumnIdentifiers(columns);
        table.setModel(d);
        refreshMatches();
    }

    public void refreshMatches() {
        Main.send("refreshing matches");
        HashMap<Integer, Match> newMatches = new HashMap<Integer, Match>();
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (newMatches.isEmpty()) {
            matches.clear();
            d.setRowCount(0);
            return;
        }
        d.setRowCount(0);
        for (Entry<Integer, Match> en : newMatches.entrySet()) {
            d.addRow(new Object[] { en.getKey(), en.getValue().getBlue1().getName(), en.getValue().getBlue2().getName(), en.getValue().getRed1().getName(), en.getValue().getRed2().getName(), en.getValue().getBlueScore(), en.getValue().getRedScore() });
        }
        matches.clear();
        matches.putAll(newMatches);
    }

    public boolean recordMatch(int id, String blue1, String blue2, String red1, String red2, int blueScore, int redScore) {
        refreshMatches();
        if (!matches.containsKey(id)) {
            try {
                main.getSQL().query("INSERT INTO skybotMatches (matchID, blue1, blue2, red1, red2, blueScore, redScore) VALUES (" + id + ", '" + blue1 + "', '" + blue2 + "', '" + red1 + "', '" + red2 + "', " + blueScore + ", " + redScore + ")");
                refreshMatches();
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
        refreshMatches();
        if (!matches.containsKey(id)) {
            try {
                main.getSQL().query("INSERT INTO skybotMatches (matchID, blue1, blue2, red1, red2, blueScore, redScore) VALUES (" + id + ", '" + blue1 + "', '" + blue2 + "', '" + red1 + "', '" + red2 + "', " + 0 + ", " + 0 + ")");
                refreshMatches();
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
        refreshMatches();
        if (matches.containsKey(id)) {
            try {
                main.getSQL().query("UPDATE skybotMatches SET blue1='" + blue1 + "', blue2='" + blue2 + "', red1='" + red1 + "', red2='" + red2 + "', blueScore=" + blueScore + ", redScore=" + redScore + " WHERE matchID=" + id);
                refreshMatches();
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
        refreshMatches();
        if (matches.containsKey(id)) {
            try {
                main.getSQL().query("UPDATE skybotMatches SET blueScore=" + blueScore + ", redScore=" + redScore + " WHERE matchID=" + id);
                refreshMatches();
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
        refreshMatches();
        if (matches.containsKey(id)) {
            try {
                main.getSQL().query("DELETE FROM skybotMatches WHERE matchID=" + id);
                refreshMatches();
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
}
