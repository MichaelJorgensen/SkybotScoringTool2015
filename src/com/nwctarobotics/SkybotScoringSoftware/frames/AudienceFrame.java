package com.nwctarobotics.SkybotScoringSoftware.frames;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.nwctarobotics.SkybotScoringSoftware.Main;

public class AudienceFrame extends JFrame {

    private JPanel contentPane;
    private int mode = 0;
    public static boolean deployed = false;

    public AudienceFrame(final Main main) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        final JLabel lblLastMatch = new JLabel("SCOREBOARD");
        lblLastMatch.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblLastMatch.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblLastMatch, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(new BorderLayout(0, 0));

        final JScrollPane scrollPane = new JScrollPane();
        panel.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setViewportView(main.getMainFrame().getResultsTable());

        JPanel panel_1 = new JPanel();
        contentPane.add(panel_1, BorderLayout.SOUTH);

        JButton btnClose = new JButton("Close");
        panel_1.add(btnClose);

        JButton btnSwitchView = new JButton("Switch View");
        btnSwitchView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (mode == 0) {
                    mode = 1;
                    lblLastMatch.setText("MATCH SCHEDULE");
                    scrollPane.setViewportView(main.getMainFrame().getMatchTable());
                    main.getMainFrame().resetResultsView();
                } else {
                    mode = 0;
                    lblLastMatch.setText("SCOREBOARD");
                    scrollPane.setViewportView(main.getMainFrame().getResultsTable());
                    main.getMainFrame().resetMatchView();
                }
            }
        });
        panel_1.add(btnSwitchView);
        btnClose.addActionListener(new AudienceFrameListener(main, this));
        setVisible(true);
        deployed = true;
    }

}
