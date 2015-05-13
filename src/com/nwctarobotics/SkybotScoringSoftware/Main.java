package com.nwctarobotics.SkybotScoringSoftware;

import java.awt.EventQueue;
import java.io.File;
import java.sql.SQLException;

import javax.swing.JFileChooser;

import com.nwctarobotics.SkybotScoringSoftware.frames.ErrorFrame;
import com.nwctarobotics.SkybotScoringSoftware.frames.MainFrame;
import com.nwctarobotics.SkybotScoringSoftware.sql.SQL;
import com.nwctarobotics.SkybotScoringSoftware.sql.options.SQLiteOptions;

public class Main {
    
    private SQL sql;
    private TeamHandler h;
    private MatchHandler m;
    
    private MainFrame mainFrame;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Main();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public Main() {
        setupSQL();
        mainFrame = new MainFrame(this);
        h = new TeamHandler(this, mainFrame.getTeamList());
        m = new MatchHandler(this, mainFrame.getTextArea());
    }
    
    public static void send(String message) {
        System.out.println(message);
    }
    
    public static void error(String error) {
        new ErrorFrame(error);
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
    
    private void setupSQL() {
        sql = new SQL(new SQLiteOptions(new File(new JFileChooser().getFileSystemView().getDefaultDirectory() + "\\Skybot.db")));
        send("Opening connection to db");
        try {
            sql.open();
            sql.createTable("CREATE TABLE IF NOT EXISTS skybotTeams (teamName VARCHAR(255))");
            sql.createTable("CREATE TABLE IF NOT EXISTS skybotMatches (matchID INT(23), blue1 VARCHAR(255), blue2 VARCHAR(255), red1 VARCHAR(255), red2 VARCHAR(255), blueScore INT(23), redScore INT(23))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
