package com.nwctarobotics.SkybotScoringSoftware;

import java.awt.TextArea;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;

public class MatchHandler {

    private Main main;
    private TextArea list;
    private HashMap<Integer, Match> matches = new HashMap<Integer, Match>();

    public MatchHandler(Main main, TextArea list) {
        this.main = main;
        this.list = list;
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
            list.setText("No matches yet");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Entry<Integer, Match> en : newMatches.entrySet()) {
            sb.append("Match " + en.getKey() + ": Blue1: " + en.getValue().getBlue1().getName() + ", Blue2: " + en.getValue().getBlue2().getName() + ", Red1: " + en.getValue().getRed1().getName() + ", Red2: " + en.getValue().getRed2().getName() + ", BlueScore: " + en.getValue().getBlueScore() + ", RedScore: " + en.getValue().getRedScore() + "\n");
        }
        list.setText(sb.toString());
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
