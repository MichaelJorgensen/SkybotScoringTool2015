package com.nwctarobotics.SkybotScoringSoftware.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AudienceFrameListener implements ActionListener {

    private AudienceFrame frame;

    public AudienceFrameListener(AudienceFrame frame) {
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent e) {
        frame.setVisible(false);
        frame.dispose();
        AudienceFrame.deployed = false;
    }
}
