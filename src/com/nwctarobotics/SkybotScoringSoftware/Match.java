package com.nwctarobotics.SkybotScoringSoftware;

public class Match {

    private int id;
    private Team blue1;
    private Team blue2;
    private Team red1;
    private Team red2;
    private int blueScore;
    private int redScore;

    public Match(int id, Team blue1, Team blue2, Team red1, Team red2, int blueScore, int redScore) {
        this.id = id;
        this.blue1 = blue1;
        this.blue2 = blue2;
        this.red1 = red1;
        this.red2 = red2;
        this.blueScore = blueScore;
        this.redScore = redScore;
    }

    public int getId() {
        return id;
    }

    public Team getBlue1() {
        return blue1;
    }

    public Team getBlue2() {
        return blue2;
    }

    public Team getRed1() {
        return red1;
    }

    public Team getRed2() {
        return red2;
    }

    public int getBlueScore() {
        return blueScore;
    }

    public int getRedScore() {
        return redScore;
    }
}
