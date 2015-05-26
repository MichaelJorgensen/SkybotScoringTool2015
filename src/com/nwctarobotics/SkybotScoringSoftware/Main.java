package com.nwctarobotics.SkybotScoringSoftware;

import java.awt.EventQueue;
import java.io.File;
import java.sql.SQLException;

import com.nwctarobotics.SkybotScoringSoftware.frames.ErrorFrame;
import com.nwctarobotics.SkybotScoringSoftware.frames.LoadingFrame;
import com.nwctarobotics.SkybotScoringSoftware.frames.MainFrame;
import com.nwctarobotics.SkybotScoringSoftware.sql.SQL;
import com.nwctarobotics.SkybotScoringSoftware.sql.options.SQLiteOptions;

public class Main {

    private SQL sql;
    private TeamHandler h;
    private MatchHandler m;

    private MainFrame mainFrame;
    private static LoadingFrame loadingFrame;

    public static void main(String[] args) {
        loadingFrame = new LoadingFrame();
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Main();
                } catch (Exception e) {
                    loadingFrame.destroy();
                    Main.error("Error loading program, message: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }

    public Main() {
        setupSQL();
        mainFrame = new MainFrame(this);
        h = new TeamHandler(this, mainFrame.getTeamList(), mainFrame.getResultsTable());
        m = new MatchHandler(this, mainFrame.getMatchTable());
        h.refreshResults();
        loadingFrame.destroy();
    }

    public static void send(String message) {
        System.out.println(message);
    }

    public static void error(String error) {
        new ErrorFrame(error);
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public SQL getSQL() {
        return sql;
    }

    public TeamHandler getTeamHandler() {
        return h;
    }

    public MatchHandler getMatchHandler() {
        return m;
    }

    public void setProgressBar(int sub, int full) {
        mainFrame.getProgressBar().setMaximum(full);
        mainFrame.getProgressBar().setValue(sub);
    }

    private void setupSQL() {
        sql = new SQL(new SQLiteOptions(new File("Skybot.db")));
        send("Opening connection to db");
        try {
            sql.open();
            sql.createTable("CREATE TABLE IF NOT EXISTS skybotTeams (teamName VARCHAR(255))");
            sql.createTable("CREATE TABLE IF NOT EXISTS skybotMatches (matchID INT(23), blue1 VARCHAR(255), blue2 VARCHAR(255), red1 VARCHAR(255), red2 VARCHAR(255), blueScore INT(23), redScore INT(23))");
        } catch (SQLException e) {
            e.printStackTrace();
            Main.error("Error connecting to DB: " + e.getMessage());
        }
    }
}
