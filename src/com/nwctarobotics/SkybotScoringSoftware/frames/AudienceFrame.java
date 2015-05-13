package com.nwctarobotics.SkybotScoringSoftware.frames;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class AudienceFrame extends JFrame {

    private JPanel contentPane;
    public static boolean deployed = false;

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
        btnClose.addActionListener(new AudienceFrameListener(this));
        contentPane.add(btnClose, BorderLayout.SOUTH);
        setVisible(true);
        deployed = true;
    }

}
