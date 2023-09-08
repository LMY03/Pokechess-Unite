package com.pokechess.controller;

import com.pokechess.model.board.Tile;
import com.pokechess.model.characters.Player;
import com.pokechess.model.characters.Pokemon;
import com.pokechess.model.characters.battletype.*;
import com.pokechess.view.components.BoardScreen;

import java.awt.*;

public class BoardManager extends Manager {

    private BoardScreen view;

    private Player player;
    private Player enemy;

    private boolean isTurn;

    private int turn;
    private int brd_com;

    private Pokemon selectedPKM;

    public BoardManager(GameManager gameManager, Pokemon[] my_PKMs, Pokemon[] enemy_PKMs) {
        super(gameManager);
        this.player = new Player("PLAYER");
        this.enemy = new Player("ENEMY");

        this.player.setPKMs(my_PKMs);
        this.enemy.setPKMs(enemy_PKMs);

//        this.autorun();

        this.initGame();
        this.view = new BoardScreen(this);
        this.run();
    }

    private void autorun() {

        this.player.setPKMs(new Pokemon[5]);
        this.enemy.setPKMs(new Pokemon[5]);

        this.player.getPKMs()[0] = new Attacker("CINDERACE", "BLAZE KICK", "FIENT");
        this.player.getPKMs()[1] = new All_Rounder("GARCHOMP", "DRAGON RUSH", "DIG");
        this.player.getPKMs()[2] = new Speedster("GENGAR", "SLUDGE BOMB", "HEX");
        this.player.getPKMs()[3] = new Supporter("WIGGLYTUFF", "DOUBLE SLAP", "SING");
        this.player.getPKMs()[4] = new Defender("SLOWBRO", "SURF", "TELEKENISIS");

        this.enemy.getPKMs()[0] = new Attacker("CRAMORANT", "SURF", "DIVE");
        this.enemy.getPKMs()[1] = new All_Rounder("MACHAMP", "CLOSE COMBAT", "BULK UP");
        this.enemy.getPKMs()[2] = new Speedster("ABSOL", "PURSUIT", "FIENT");
        this.enemy.getPKMs()[3] = new Defender("CRUSTLE", "X-SCISSOR", "SHELL SMASH");
        this.enemy.getPKMs()[4] = new Supporter("MRMIME", "CONFUSION", "BARRIER");

        for (int i = 0; i < 5; i++) {
            this.player.getPKMs()[i].setPKM(1, i, 0);
            this.enemy.getPKMs()[i].setPKM(2, i, 6);
        }
    }

    private void initGame() {
        this.roll();
        this.turn = 1;
        this.brd_com = 1;
    }

    public void run() {
        this.initTurn();
        if (this.turn <= 20) {
            if (this.player.isTurn()) {
                this.playerTurn(this.player);
            }
            else if (this.enemy.isTurn()) {
                this.playerTurn(this.enemy);
            }
        }
        else {
            this.openFinalScreen();
        }
    }

    private void initTurn() {
        this.brd_com = 1;
        this.regenerate(this.player.getPKMs());
        this.regenerate(this.enemy.getPKMs());
        this.revive(this.player.getPKMs());
        this.revive(this.enemy.getPKMs());
    }

    private void playerTurn(Player player) {
        this.view.getPlayerTurn().setText(player.getName() + "'s TURN");
    }

    private void regenerate(Pokemon[] PKMs) {
        for (int i = 0; i < PKMs.length; i++)
            if (PKMs[i].getCol() == PKMs[i].getHomeCol())
                if (PKMs[i].getRow() == PKMs[i].getHomeRow())
                    PKMs[i].regenerate();
    }
    
    private void revive(Pokemon[] PKMs) {
        for (int i = 0; i < PKMs.length; i++) {
            if (!PKMs[i].isAlive()) {
                if (PKMs[i].getRev_turn() == this.turn) {
                    this.view.getBoard().getTiles()[PKMs[i].getHomeRow()][PKMs[i].getHomeCol()].place(PKMs[i]);
                    PKMs[i].setHealth(PKMs[i].getMaxHealth());
                    PKMs[i].setRow(PKMs[i].getHomeRow());
                    PKMs[i].setCol(PKMs[i].getHomeCol());
                    PKMs[i].setIsAlive(true);
                    PKMs[i].setRev_turn(0);
                    this.refreshBoard();
                }
            }
        }
    }

    public void selectPKM(Object source) {
        for (int i = 0; i < this.player.getPKMs().length; i++) {
            if (source == this.player.getPKMs()[i].getTile()) {
                if (this.isTurn) {
                    if (this.selectedPKM != null)
                        this.view.display();
                    this.selectedPKM = this.player.getPKMs()[i];
                    if (this.selectedPKM.getPlayer() == 1)
                        this.showActions(this.selectedPKM);
                    else
                        this.selectedPKM = null;
                }
            }
            if (source == this.enemy.getPKMs()[i].getTile()) {
                if (!this.isTurn) {
                    if (this.selectedPKM != null)
                        this.view.display();
                    this.selectedPKM = this.enemy.getPKMs()[i];
                    this.showActions(this.selectedPKM);
                }
            }
        }
    }

    private void showActions(Pokemon PKM) {
        for (int i = -PKM.getSpeed(); i < PKM.getSpeed() + 1; i++) {
            for (int j = -PKM.getSpeed(); j < PKM.getSpeed() + 1; j++) {
                int row = PKM.getRow() + i;
                int col = PKM.getCol() + j;
                if (this.checkBounds(row, col)) {
                    this.move(PKM, row, col);
                    this.dunk(PKM);
                    this.battle(PKM, row, col);
                }
            }
        }
    }

    private void move(Pokemon PKM, int row, int col) {
        Tile tile = this.view.getBoard().getTiles()[row][col];
        if (this.checkTile(row, col, PKM.getHomeRow())) {
            if (!this.view.getBoard().hasPKM(tile)) {
                if (PKM.getPlayer() == 1) {
                    if (col != 6) {
                        if (col == 0) {
                            if (PKM.getHomeRow() == row) {
                                tile.setOpaque(true);
                                tile.setBackground(Color.CYAN);
                            }
                        }
                        else {
                            tile.setOpaque(true);
                            tile.setBackground(Color.CYAN);
                        }
                    }
                }
                else {
                    if (col != 0) {
                        if (col == 6) {
                            if (PKM.getHomeRow() == row) {
                                tile.setOpaque(true);
                                tile.setBackground(Color.CYAN);
                            }
                        }
                        else {
                            tile.setOpaque(true);
                            tile.setBackground(Color.CYAN);
                        }
                    }
                }
            }
        }
    }

    private void battle(Pokemon PKM, int row, int col) {
        Tile tile = this.view.getBoard().getTiles()[row][col];
        if (tile.hasPKM()) {
            if (tile.hasEnemy(PKM)) {
                if (PKM.getPlayer() == 1) {
                    if (col != 6) {
                        tile.setOpaque(true);
                        tile.setBackground(Color.RED);
                    }
                    if (PKM.getCol() == 5){
                        if (this.view.getBoard().getTiles()[PKM.getRow()][6].hasPKM()) {
                            this.view.getBoard().getTiles()[PKM.getRow()][6].setOpaque(true);
                            this.view.getBoard().getTiles()[PKM.getRow()][6].setBackground(Color.RED);
                        }
                    }
                }
                else {
                    if (col != 0) {
                        tile.setOpaque(true);
                        tile.setBackground(Color.RED);
                    }
                    if (PKM.getCol() == 1){
                        if (this.view.getBoard().getTiles()[PKM.getRow()][0].hasPKM()) {
                            this.view.getBoard().getTiles()[PKM.getRow()][0].setOpaque(true);
                            this.view.getBoard().getTiles()[PKM.getRow()][0].setBackground(Color.RED);
                        }
                    }
                }
            }
        }
    }

    private void dunk(Pokemon PKM) {
        int col;
        int enemyCol;
        if (PKM.getPlayer() == 1) {
            col = 5;
            enemyCol = 6;
        }
        else {
            col = 1;
            enemyCol = 0;
        }
        if (PKM.getCol() == col) {
            Tile tile = this.view.getBoard().getTiles()[PKM.getRow()][enemyCol];
            if (!this.view.getBoard().hasGoalDef(PKM)) {
                if (!tile.hasPKM()) {
                    tile.setOpaque(true);
                    tile.setBackground(Color.YELLOW);
                }
            }
            else {
                if (!this.view.getBoard().getTiles()[PKM.getRow()][enemyCol].hasPKM()) {
                    tile.setOpaque(true);
                    tile.setBackground(Color.ORANGE);
                }
            }
        }
    }

    public void boardCommand(Object source) {
        for (int row = 0; row < this.view.getBoard().getTiles().length; row++) {
            for (int col = 0; col < this.view.getBoard().getTiles()[0].length; col++) {
                Tile tile = this.view.getBoard().getTiles()[row][col];
                this.goalKeepingBattle(source, tile);
                this.moveCommand(source, row, col);
                this.battleCommand(source, tile);
                this.dunkCommand(source, tile);
            }
        }
    }

    private void moveCommand(Object source, int row, int col) {
        Tile tile = this.view.getBoard().getTiles()[row][col];
        if (tile.getBackground() == Color.CYAN) {
            if (source == tile) {
                this.selectedPKM.move(tile, row, col);
                this.actionPerformed();
            }
        }
    }

    private void battleCommand(Object source, Tile tile) {
        if (tile.getBackground() == Color.RED) {
            if (source == tile) {
                this.openBattleScreen(this.selectedPKM, tile.getPKM());
                this.actionPerformed();
            }
        }
    }

    private void goalKeepingBattle(Object source, Tile tile) {
        if (tile.getBackground() == Color.ORANGE)
            if (source == tile)
                this.openBattleScreen(this.selectedPKM, this.view.getBoard().getGoalDef(this.selectedPKM));
    }

    private void dunkCommand(Object source, Tile tile) {
        if (tile.getBackground() == Color.YELLOW) {
            if (source == tile) {
                if (this.selectedPKM.getPlayer() == 1)
                    this.player.addTeam_pts(this.selectedPKM);
                else
                    this.enemy.addTeam_pts(this.selectedPKM);
                this.actionPerformed();
            }
        }
    }

    private boolean checkBounds(int row, int col) {
        return row > -1 && col > -1 && row < 5 && col < 7;
    }

    private boolean checkTile(int row, int col, int home) {
        if (col == 0 && row == home)
            return true;
        else if (col != 0)
            return true;
        else
            return false;
    }

    public void actionPerformed() {
        this.brd_com--;
        if (this.selectedPKM != null)
            this.selectedPKM = null;

        if (this.brd_com == 0) {
            if (this.player.isTurn() && this.enemy.isTurn()) {
                this.player.setIsTurn(false);
                this.enemy.setIsTurn(false);
                this.turn++;
                this.view.display();
                this.roll();
                this.run();
            }
            else if (this.player.isTurn()) {
                this.brd_com = 1;
                this.isTurn = false;
                this.enemy.setIsTurn(true);
                this.playerTurn(this.enemy);
            }
            else if (this.enemy.isTurn()) {
                this.brd_com = 1;
                this.isTurn = true;
                this.player.setIsTurn(true);
                this.playerTurn(this.player);
            }
        }
        if (this.player.getTeam_pts() > this.enemy.getTeam_pts()) {
            this.player.setIsWin(true);
            this.enemy.setIsWin(false);
        }
        else if (this.player.getTeam_pts() < this.enemy.getTeam_pts()) {
            this.player.setIsWin(false);
            this.enemy.setIsWin(true);
        }
        this.refreshBoard();
    }

    private void refreshBoard() {
        this.view.display();
    }

    private void roll() {
        Double roll = Math.random();
        if (roll > 0.50) {
            this.player.setIsTurn(true);
            this.isTurn = true;
        }
        else {
            this.enemy.setIsTurn(true);
            this.isTurn = false;
        }
    }

    public Player getPlayer() {
        return this.player;
    }

    public Player getEnemy() {
        return this.enemy;
    }

    public Pokemon getSelectedPKM() {
        return this.selectedPKM;
    }

    public int getTurn() {
        return this.turn;
    }

    @Override
    public BoardScreen getView() {
        return this.view;
    }
}
