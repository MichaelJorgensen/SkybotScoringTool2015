package com.nwctarobotics.SkybotScoringSoftware.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorFrameListener implements ActionListener {

    private ErrorFrame frame;

    public ErrorFrameListener(ErrorFrame frame) {
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent e) {
        frame.setVisible(false);
        frame.dispose();
    }
}
