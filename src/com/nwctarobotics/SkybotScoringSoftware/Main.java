package com.nwctarobotics.SkybotScoringSoftware;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.nwctarobotics.SkybotScoringSoftware.sql.SQL;
import com.nwctarobotics.SkybotScoringSoftware.sql.options.SQLiteOptions;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class Main {

    private JFrame frmSkybotScoringSoftware;
    private JTextField textField;
    private JTextField textField_1;
    
    private SQL sql;
    private TeamHandler h;
    private JTextField textField_2;
    private JButton btnRefresh;
    private JPanel panel;
    private JPanel panel_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main window = new Main();
                    window.frmSkybotScoringSoftware.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Main() {
        initialize();
        setupSQL();        
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBorder(null);
        tabbedPane.setBounds(0, 0, 699, 639);
        frmSkybotScoringSoftware.getContentPane().add(tabbedPane);
        
        panel = new JPanel();
        panel.setBorder(null);
        tabbedPane.addTab("Teams", null, panel, null);
        panel.setLayout(null);
        
        textField = new JTextField();
        textField.setBounds(32, 11, 86, 20);
        panel.add(textField);
        textField.setColumns(10);
        
        JButton btnRemoveTeam = new JButton("Add Team");
        btnRemoveTeam.setBounds(32, 63, 125, 23);
        panel.add(btnRemoveTeam);
        
        textField_1 = new JTextField();
        textField_1.setBounds(245, 22, 125, 20);
        panel.add(textField_1);
        textField_1.setColumns(10);
        
        JButton button = new JButton("Remove Team");
        button.setBounds(267, 63, 125, 23);
        panel.add(button);
        
        JLabel lblTeamList = new JLabel("Team List");
        lblTeamList.setBounds(171, 156, 67, 14);
        panel.add(lblTeamList);
        lblTeamList.setHorizontalAlignment(SwingConstants.CENTER);
        
        textField_2 = new JTextField();
        textField_2.setBounds(127, 200, 193, 20);
        panel.add(textField_2);
        textField_2.setColumns(10);
        
        btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(151, 231, 89, 23);
        panel.add(btnRefresh);
        
        panel_1 = new JPanel();
        tabbedPane.addTab("Matches", null, panel_1, null);
        panel_1.setLayout(null);
        btnRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                h.refreshTeams();
            }
        });
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (h.removeTeam(textField_1.getText())) {
                    textField_1.setText(null);
                }
            }
        });
        btnRemoveTeam.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (h.addTeam(textField.getText())) {
                    textField.setText(null);
                }
            }
        });
        h = new TeamHandler(this, textField_2);
    }
    
    private void send(String message) {
        System.out.println(message);
    }
    
    public SQL getSQL() {
        return sql;
    }
    
    private void setupSQL() {
        SQLiteOptions op = new SQLiteOptions(new File("C:\\Users\\Mike\\Desktop\\Skybot.db"));
        sql = new SQL(op);
        send("Opening connection to db");
        try {
            sql.open();
            sql.createTable("CREATE TABLE IF NOT EXISTS skybotTeams (teamName VARCHAR(255))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmSkybotScoringSoftware = new JFrame();
        frmSkybotScoringSoftware.setResizable(false);
        frmSkybotScoringSoftware.setTitle("Skybot Scoring Software");
        frmSkybotScoringSoftware.setBounds(100, 100, 715, 678);
        frmSkybotScoringSoftware.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmSkybotScoringSoftware.getContentPane().setLayout(null);
    }
}
