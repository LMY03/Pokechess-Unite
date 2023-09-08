package com.pokechess.model.characters;

public class Player {

    private String name;

    private Pokemon[] PKMs;

    private int team_pts;
    private boolean isTurn;

    private boolean isWin;

    public Player(String name) {
        this.name = name;
        this.team_pts = 0;
    }

    public String getName() {
        return this.name;
    }

    public Pokemon[] getPKMs() {
        return this.PKMs;
    }

    public void setPKMs(Pokemon[] PKMs) {
        this.PKMs = PKMs;
    }

    public int getTeam_pts() {
        return this.team_pts;
    }

    public void addTeam_pts(Pokemon PKM) {
        this.team_pts += PKM.getPoint();
    }

    public boolean isTurn() {
        return this.isTurn;
    }

    public void setIsTurn(boolean turn) {
        this.isTurn = turn;
    }

    public boolean isWin() {
        return this.isWin;
    }

    public void setIsWin(boolean isWin) {
        this.isWin = isWin;
    }
}
