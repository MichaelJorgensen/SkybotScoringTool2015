package com.nwctarobotics.SkybotScoringSoftware.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class LoadingFrame extends JFrame {

    private JPanel contentPane;
    private JLabel desc;

    public LoadingFrame() {
        setTitle("Loading Skybot Scoring Software");
        setResizable(false);
        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 649, 152);
        contentPane = new JPanel();
        contentPane.setBackground(Color.BLACK);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JLabel lblLoading = new JLabel("LOADING");
        lblLoading.setFont(new Font("LCD", Font.PLAIN, 40));
        lblLoading.setHorizontalAlignment(SwingConstants.CENTER);
        lblLoading.setForeground(Color.WHITE);
        contentPane.add(lblLoading, BorderLayout.CENTER);

        desc = new JLabel("Loading database...");
        desc.setFont(new Font("LCD", Font.PLAIN, 20));
        desc.setHorizontalAlignment(SwingConstants.CENTER);
        desc.setForeground(Color.WHITE);
        contentPane.add(desc, BorderLayout.SOUTH);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void destroy() {
        setVisible(false);
        dispose();
    }
}
