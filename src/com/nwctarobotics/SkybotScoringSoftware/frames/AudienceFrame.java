package com.nwctarobotics.SkybotScoringSoftware.frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;

public class AudienceFrame extends JFrame {

    private JPanel contentPane;

    public AudienceFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JLabel lblLastMatch = new JLabel("LAST MATCH");
        lblLastMatch.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblLastMatch.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblLastMatch, BorderLayout.NORTH);
        
        JButton btnClose = new JButton("Close");
        contentPane.add(btnClose, BorderLayout.SOUTH);
        setVisible(true);
    }

}
