package com.nwctarobotics.SkybotScoringSoftware.frames;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ErrorFrame extends JFrame {

    private JPanel contentPane;

    public ErrorFrame(String error) {
        setResizable(false);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 461, 171);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel(error);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
        lblNewLabel.setBounds(10, 11, 425, 81);
        contentPane.add(lblNewLabel);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ErrorFrameListener(this));
        btnClose.setBounds(176, 103, 89, 23);
        contentPane.add(btnClose);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
