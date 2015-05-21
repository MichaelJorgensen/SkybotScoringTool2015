package com.nwctarobotics.SkybotScoringSoftware;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class TeamHandler {

    private Main main;
    private JTextField teamList;
    private HashMap<String, Team> teams = new HashMap<String, Team>();

    private DefaultTableModel d = new DefaultTableModel(0, 4);
    private String[] columns = new String[] { "Team Name", "Wins", "Losses", "Average Score" };

    public TeamHandler(Main main, JTextField teamList, JTable resultsTable) {
        this.main = main;
        this.teamList = teamList;
        d.setColumnIdentifiers(columns);
        resultsTable.setModel(d);
        refreshTeams();
    }

    public void refreshTeams() {
        Main.send("Refreshing teams");
        HashMap<String, Team> newTeams = new HashMap<String, Team>();
        try {
            ResultSet rs = main.getSQL().query("SELECT teamName FROM skybotTeams");
            while (rs.next()) {
                String name = rs.getString("teamName");
                newTeams.put(name, new Team(name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (newTeams.isEmpty()) {
            teamList.setText(null);
            teams.clear();
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (String t : newTeams.keySet()) {
            sb.append(t + ", ");
        }
        sb.delete(sb.length() - 2, sb.length() - 1);
        teamList.setText(sb.toString());
        teams.clear();
        teams.putAll(newTeams);
    }

    public void refreshResults() {
        Main.send("Refreshing results..");
        if (teams.isEmpty()) {
            Main.error("There are no teams for which to calculate the results for");
            return;
        }
    }

    public boolean addTeam(String name) {
        if (name == null || name.isEmpty() || name.replaceAll(" ", "").isEmpty()) {
            Main.error("No Name Given");
            return false;
        }
        Main.send("adding team: " + name);
        refreshTeams();
        if (!teams.containsKey(name)) {
            try {
                main.getSQL().query("INSERT INTO skybotTeams (teamName) VALUES ('" + name + "')");
                refreshTeams();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            Main.error("Team Already Exists");
        }
        return false;
    }

    public boolean removeTeam(String name) {
        if (name == null || name.isEmpty() || name.replaceAll(" ", "").isEmpty()) {
            Main.error("No Name Given");
            return false;
        }
        refreshTeams();
        if (teams.containsKey(name)) {
            try {
                main.getSQL().query("DELETE FROM skybotTeams WHERE teamName='" + name + "'");
                refreshTeams();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            Main.error("Team Doesn't Exist");
        }
        return false;
    }
}
