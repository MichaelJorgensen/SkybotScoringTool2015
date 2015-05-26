package com.nwctarobotics.SkybotScoringSoftware;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TeamHandler {

    private Main main;
    private JTextField teamList;
    private HashMap<String, Team> teams = new HashMap<String, Team>();

    private DefaultTableModel d;
    private String[] columns = new String[] { "Team Name", "Wins", "Losses", "Ties", "Average Score" };

    public TeamHandler(Main main, JTextField teamList, JTable resultsTable) {
        this.main = main;
        this.teamList = teamList;
        d = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 4) {
                    return Double.class;
                }
                return super.getColumnClass(column);
            }
        };
        d.setColumnIdentifiers(columns);
        resultsTable.setModel(d);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        resultsTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        resultsTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        resultsTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        resultsTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        resultsTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        refreshTeams(false);
    }

    public void refreshTeams(boolean updateRanks) {
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
        if (updateRanks)
            refreshResults();
    }

    public void refreshResults() {
        Main.send("Refreshing results..");
        if (teams.isEmpty()) {
            Main.send("There are no teams for which to calculate the results for");
            return;
        }
        d.setRowCount(0);
        HashMap<Integer, Match> matches = main.getMatchHandler().getMatches();
        for (String t : teams.keySet()) {
            double wins = 0;
            double losses = 0;
            double ties = 0;
            double totalScore = 0;
            for (Entry<Integer, Match> en : matches.entrySet()) {
                if (en.getValue().getBlueScore() == -1 || en.getValue().getRedScore() == -1) {
                    continue;
                }
                MatchPosition p = en.getValue().getTeamPosition(t);
                if (p != null) {
                    if (p == MatchPosition.Blue1 || p == MatchPosition.Blue2) {
                        totalScore += en.getValue().getBlueScore();
                        if (en.getValue().getBlueScore() > en.getValue().getRedScore()) {
                            wins++;
                        } else if (en.getValue().getBlueScore() < en.getValue().getRedScore()) {
                            losses++;
                        } else {
                            ties++;
                        }
                    } else {
                        totalScore += en.getValue().getRedScore();
                        if (en.getValue().getBlueScore() > en.getValue().getRedScore()) {
                            losses++;
                        } else if (en.getValue().getBlueScore() < en.getValue().getRedScore()) {
                            wins++;
                        } else {
                            ties++;
                        }
                    }
                }
            }
            double averageScore = totalScore / (wins + losses + ties);
            if (totalScore == 0) {
                averageScore = 0;
            }
            d.addRow(new Object[] { t, (int) wins, (int) losses, (int) ties, averageScore });
        }
    }

    public boolean addTeam(String name) {
        if (name == null || name.isEmpty() || name.replaceAll(" ", "").isEmpty()) {
            Main.error("No Name Given");
            return false;
        }
        Main.send("adding team: " + name);
        refreshTeams(false);
        if (!teams.containsKey(name)) {
            try {
                main.getSQL().query("INSERT INTO skybotTeams (teamName) VALUES ('" + name + "')");
                refreshTeams(true);
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
        refreshTeams(false);
        if (teams.containsKey(name)) {
            try {
                main.getSQL().query("DELETE FROM skybotTeams WHERE teamName='" + name + "'");
                refreshTeams(true);
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
