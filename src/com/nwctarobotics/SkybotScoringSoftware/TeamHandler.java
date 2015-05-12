package com.nwctarobotics.SkybotScoringSoftware;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JTextField;

public class TeamHandler {

    private Main main;
    private JTextField teamList;
    private HashMap<String, Team> teams = new HashMap<String, Team>();

    public TeamHandler(Main main, JTextField teamList) {
        this.main = main;
        this.teamList = teamList;
        refreshTeams();
    }

    public void refreshTeams() {
        send("Refreshing teams");
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
        send("TeamsRecorded: " + teams.keySet().toString());
    }

    public boolean addTeam(String name) {
        if (name == null || name.isEmpty() || name.replaceAll(" ", "").isEmpty()) {
            send("no name given");
            return false;
        }
        send("adding team: " + name);
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
            send("team already exists");
        }
        return false;
    }

    public boolean removeTeam(String name) {
        if (name == null || name.isEmpty() || name.replaceAll(" ", "").isEmpty()) {
            send("no name given");
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
            send("team doesn't exist");
        }
        return false;
    }

    private void send(String message) {
        System.out.println(message);
    }

}
