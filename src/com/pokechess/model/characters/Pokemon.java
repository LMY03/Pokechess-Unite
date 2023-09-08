package com.pokechess.model.characters;

import com.pokechess.model.board.Tile;
import com.pokechess.model.characters.battletype.*;

public class Pokemon {

    private Tile tile;

    private boolean isAlive;

    private int player;
    private String name;
    private String attackMove;
    private String defenseMove;

    private int homeRow;
    private int homeCol;

    private int row;
    private int col;

    protected int maxHealth;
    protected int health;
    protected int attack; // percentage
    protected int defense; // percentage
    protected int speed; // no of tiles
    protected int gen; // percentage
    protected int rev; // no of turns

    private int point; // dunk points
    private int rev_turn;

    private int command;

    public Pokemon(String name, String attackMove, String defenseMove) {
        this.name = name;
        this.attackMove = attackMove;
        this.defenseMove = defenseMove;
        this.isAlive = true;
        this.point = 1;
    }

    public void setPKM(int player, int row, int col) {
        this.player = player;
        this.homeRow = row;
        this.homeCol = col;
        this.row = row;
        this.col = col;
    }

    public void move(Tile tile, int row, int col) {
        tile.place(this);
        this.row = row;
        this.col = col;
    }

    public boolean isAttacker() {
        return this instanceof Attacker;
    }

    public boolean isSpeedster() {
        return this instanceof Speedster;
    }

    public boolean isAllRounder() {
        return this instanceof All_Rounder;
    }

    public boolean isDefender() {
        return this instanceof Defender;
    }

    public boolean isSupporter() {
        return this instanceof Supporter;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public boolean isAlive() {
        return this.isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public int getPlayer() {
        return this.player;
    }

    public String getName() {
        return this.name;
    }

    public String getAttackMove() {
        return this.attackMove;
    }

    public String getDefenseMove() {
        return this.defenseMove;
    }

    public int getHomeRow() {
        return this.homeRow;
    }

    public int getHomeCol() {
        return this.homeCol;
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return this.col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int newHealth) {
        if (this.maxHealth <= newHealth)
            this.health = this.maxHealth;
        else if (newHealth < 0)
            this.health = 0;
        else
            this.health = newHealth;
    }

    public int getAttack() {
        return this.attack;
    }

    public int getDefense() {
        return this.defense;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void regenerate() {
        this.setHealth((int) (this.health + (this.health * (this.gen * 0.01))));
    }

    public int getRev() {
        return this.rev;
    }

    public int getPoint() {
        return this.point;
    }

    public void setPoint(int point) {
        if (this.point >= 50)
            this.point = 50;
        else if (point < 0)
            this.point = 0;
        else
            this.point = point;
    }

    public int getRev_turn() {
        return this.rev_turn;
    }

    public void setRev_turn(int turn) {
        this.rev_turn = turn;
    }

    public int getCommand() {
        return this.command;
    }

    public void setCommand(int command) {
        this.command = command;
    }
}
