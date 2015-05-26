package com.nwctarobotics.SkybotScoringSoftware.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.nwctarobotics.SkybotScoringSoftware.Main;

public class AudienceFrameListener implements ActionListener {

    private Main main;
    private AudienceFrame frame;

    public AudienceFrameListener(Main main, AudienceFrame frame) {
        this.main = main;
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent e) {
        frame.setVisible(false);
        frame.dispose();
        main.getMainFrame().resetResultsView();
        main.getMainFrame().resetMatchView();
        AudienceFrame.deployed = false;
    }
}
