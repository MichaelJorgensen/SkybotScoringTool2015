package com.nwctarobotics.SkybotScoringSoftware.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.nwctarobotics.SkybotScoringSoftware.Main;
import javax.swing.JScrollPane;

public class MainFrame {

    private JFrame frmSkybotScoringSoftware;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JButton btnRefresh;
    private JPanel panel;
    private JPanel panel_1;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_7;
    private JTextField textField_8;
    private JTextField textField_9;
    private JTextField textField_10;
    private JTextField textField_11;
    private JTextField textField_12;
    private JTextField textField_13;
    private JTextField textField_14;
    private JTextField textField_15;
    private JTextField textField_16;
    private JTextField textField_17;
    private JTable table;

    public MainFrame(final Main main) {
        initialize();
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBorder(null);
        tabbedPane.setBounds(0, 0, 699, 639);
        frmSkybotScoringSoftware.getContentPane().add(tabbedPane);
        frmSkybotScoringSoftware.setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setBorder(null);
        tabbedPane.addTab("Teams", null, panel, null);
        panel.setLayout(null);

        textField = new JTextField();
        textField.setBounds(0, 15, 150, 20);
        panel.add(textField);
        textField.setColumns(10);

        JButton btnRemoveTeam = new JButton("Add Team");
        btnRemoveTeam.setBounds(10, 46, 125, 23);
        panel.add(btnRemoveTeam);

        textField_1 = new JTextField();
        textField_1.setBounds(175, 15, 150, 20);
        panel.add(textField_1);
        textField_1.setColumns(10);

        JButton button = new JButton("Remove Team");
        button.setBounds(185, 46, 125, 23);
        panel.add(button);

        JLabel lblTeamList = new JLabel("Team List");
        lblTeamList.setBounds(10, 80, 67, 14);
        panel.add(lblTeamList);
        lblTeamList.setHorizontalAlignment(SwingConstants.CENTER);

        textField_2 = new JTextField();
        textField_2.setBounds(0, 100, 684, 20);
        panel.add(textField_2);
        textField_2.setColumns(10);

        btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(10, 131, 89, 23);
        panel.add(btnRefresh);

        panel_1 = new JPanel();
        tabbedPane.addTab("Matches", null, panel_1, null);
        panel_1.setLayout(null);

        JLabel lblRecordNewMatch = new JLabel("Record New Match");
        lblRecordNewMatch.setBounds(294, 0, 121, 14);
        panel_1.add(lblRecordNewMatch);

        JLabel lblModifyOldMatch = new JLabel("Modify Old Match");
        lblModifyOldMatch.setBounds(294, 181, 104, 14);
        panel_1.add(lblModifyOldMatch);

        JLabel lblDeleteMatch = new JLabel("Delete Match");
        lblDeleteMatch.setBounds(290, 370, 81, 14);
        panel_1.add(lblDeleteMatch);

        textField_3 = new JTextField();
        textField_3.setBounds(10, 36, 86, 20);
        panel_1.add(textField_3);
        textField_3.setColumns(10);

        textField_4 = new JTextField();
        textField_4.setBounds(106, 36, 86, 20);
        panel_1.add(textField_4);
        textField_4.setColumns(10);

        textField_5 = new JTextField();
        textField_5.setBounds(202, 36, 86, 20);
        panel_1.add(textField_5);
        textField_5.setColumns(10);

        textField_6 = new JTextField();
        textField_6.setBounds(297, 36, 86, 20);
        panel_1.add(textField_6);
        textField_6.setColumns(10);

        textField_7 = new JTextField();
        textField_7.setBounds(393, 36, 86, 20);
        panel_1.add(textField_7);
        textField_7.setColumns(10);

        textField_8 = new JTextField();
        textField_8.setBounds(489, 36, 86, 20);
        panel_1.add(textField_8);
        textField_8.setColumns(10);

        textField_9 = new JTextField();
        textField_9.setBounds(585, 36, 86, 20);
        panel_1.add(textField_9);
        textField_9.setColumns(10);

        JLabel lblMatch = new JLabel("Match #");
        lblMatch.setBounds(26, 21, 46, 14);
        panel_1.add(lblMatch);

        JLabel lblBlue = new JLabel("Blue 1");
        lblBlue.setBounds(122, 21, 46, 14);
        panel_1.add(lblBlue);

        JLabel lblBlue_1 = new JLabel("Blue 2");
        lblBlue_1.setBounds(218, 21, 46, 14);
        panel_1.add(lblBlue_1);

        JLabel lblRed = new JLabel("Red 1");
        lblRed.setBounds(320, 21, 46, 14);
        panel_1.add(lblRed);

        JLabel lblRed_1 = new JLabel("Red 2");
        lblRed_1.setBounds(411, 21, 46, 14);
        panel_1.add(lblRed_1);

        JLabel lblBlueScore = new JLabel("Blue Score");
        lblBlueScore.setBounds(499, 21, 65, 14);
        panel_1.add(lblBlueScore);

        JLabel lblRedScore = new JLabel("Red Score");
        lblRedScore.setBounds(593, 21, 67, 14);
        panel_1.add(lblRedScore);

        JButton btnRecord = new JButton("Record");
        btnRecord.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (main.getMatchHandler().recordMatch(Integer.valueOf(textField_3.getText()), textField_4.getText(), textField_5.getText(), textField_6.getText(), textField_7.getText(), Integer.valueOf(textField_8.getText()), Integer.valueOf(textField_9.getText()))) {
                        textField_3.setText(null);
                        textField_4.setText(null);
                        textField_5.setText(null);
                        textField_6.setText(null);
                        textField_7.setText(null);
                        textField_8.setText(null);
                        textField_9.setText(null);
                    }
                } catch (NumberFormatException ex) {
                    Main.error("Number expected, but got " + ex.getMessage());
                }
            }
        });
        btnRecord.setBounds(294, 82, 89, 23);
        panel_1.add(btnRecord);

        textField_10 = new JTextField();
        textField_10.setColumns(10);
        textField_10.setBounds(10, 225, 86, 20);
        panel_1.add(textField_10);

        JLabel label = new JLabel("Match #");
        label.setBounds(26, 210, 46, 14);
        panel_1.add(label);

        textField_11 = new JTextField();
        textField_11.setColumns(10);
        textField_11.setBounds(106, 225, 86, 20);
        panel_1.add(textField_11);

        JLabel label_1 = new JLabel("Blue 1");
        label_1.setBounds(122, 210, 46, 14);
        panel_1.add(label_1);

        textField_12 = new JTextField();
        textField_12.setColumns(10);
        textField_12.setBounds(202, 225, 86, 20);
        panel_1.add(textField_12);

        JLabel label_2 = new JLabel("Blue 2");
        label_2.setBounds(218, 210, 46, 14);
        panel_1.add(label_2);

        JLabel label_3 = new JLabel("Red 1");
        label_3.setBounds(320, 210, 46, 14);
        panel_1.add(label_3);

        textField_13 = new JTextField();
        textField_13.setColumns(10);
        textField_13.setBounds(297, 225, 86, 20);
        panel_1.add(textField_13);

        textField_14 = new JTextField();
        textField_14.setColumns(10);
        textField_14.setBounds(393, 225, 86, 20);
        panel_1.add(textField_14);

        JLabel label_4 = new JLabel("Red 2");
        label_4.setBounds(411, 210, 46, 14);
        panel_1.add(label_4);

        JLabel label_5 = new JLabel("Blue Score");
        label_5.setBounds(499, 210, 65, 14);
        panel_1.add(label_5);

        textField_15 = new JTextField();
        textField_15.setColumns(10);
        textField_15.setBounds(489, 225, 86, 20);
        panel_1.add(textField_15);

        textField_16 = new JTextField();
        textField_16.setColumns(10);
        textField_16.setBounds(585, 225, 86, 20);
        panel_1.add(textField_16);

        JLabel label_6 = new JLabel("Red Score");
        label_6.setBounds(593, 210, 67, 14);
        panel_1.add(label_6);

        textField_17 = new JTextField();
        textField_17.setColumns(10);
        textField_17.setBounds(290, 420, 86, 20);
        panel_1.add(textField_17);

        JLabel label_7 = new JLabel("Match #");
        label_7.setBounds(290, 395, 46, 14);
        panel_1.add(label_7);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (main.getMatchHandler().deleteMatch(Integer.valueOf(textField_17.getText()))) {
                        textField_17.setText(null);
                    }
                } catch (NumberFormatException ex) {
                    Main.error("Expected number, got " + ex.getMessage());
                }
            }
        });
        btnDelete.setBounds(290, 451, 89, 23);
        panel_1.add(btnDelete);

        JButton btnModify = new JButton("Modify");
        btnModify.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (main.getMatchHandler().modifyMatch(Integer.valueOf(textField_10.getText()), textField_11.getText(), textField_12.getText(), textField_13.getText(), textField_14.getText(), Integer.valueOf(textField_15.getText()), Integer.valueOf(textField_16.getText()))) {
                        textField_10.setText(null);
                        textField_11.setText(null);
                        textField_12.setText(null);
                        textField_13.setText(null);
                        textField_14.setText(null);
                        textField_15.setText(null);
                        textField_16.setText(null);
                    }
                } catch (NumberFormatException ex) {
                    Main.error("Number expected, but got " + ex.getMessage());
                }
            }
        });
        btnModify.setBounds(290, 267, 89, 23);
        panel_1.add(btnModify);

        JPanel panel_2 = new JPanel();
        tabbedPane.addTab("Match View", null, panel_2, null);
        panel_2.setLayout(null);

        JButton btnRefresh_1 = new JButton("Refresh");
        btnRefresh_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.getMatchHandler().refreshMatches();
            }
        });
        btnRefresh_1.setBounds(301, 21, 89, 23);
        panel_2.add(btnRefresh_1);

        JButton btnLaunchPrettyVersion = new JButton("Launch Audience Version");
        btnLaunchPrettyVersion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!AudienceFrame.deployed) {
                    new AudienceFrame();
                }
            }
        });
        btnLaunchPrettyVersion.setBounds(466, 21, 200, 23);
        panel_2.add(btnLaunchPrettyVersion);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 55, 674, 545);
        panel_2.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        table.setColumnSelectionAllowed(true);
        btnRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main.getTeamHandler().refreshTeams();
            }
        });
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (main.getTeamHandler().removeTeam(textField_1.getText())) {
                    textField_1.setText(null);
                }
            }
        });
        btnRemoveTeam.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (main.getTeamHandler().addTeam(textField.getText())) {
                    textField.setText(null);
                }
            }
        });
    }

    public JTextField getTeamList() {
        return textField_2;
    }

    public JTable getMatchTable() {
        return table;
    }

    private void initialize() {
        frmSkybotScoringSoftware = new JFrame();
        frmSkybotScoringSoftware.setResizable(false);
        frmSkybotScoringSoftware.setTitle("Skybot Scoring Software");
        frmSkybotScoringSoftware.setBounds(100, 100, 715, 677);
        frmSkybotScoringSoftware.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmSkybotScoringSoftware.getContentPane().setLayout(null);
        frmSkybotScoringSoftware.setVisible(true);
    }
}
