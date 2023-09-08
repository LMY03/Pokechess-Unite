package com.pokechess.controller;

import com.pokechess.model.board.Tile;
import com.pokechess.model.characters.Pokemon;
import com.pokechess.model.characters.battletype.Attacker;
import com.pokechess.model.characters.battletype.Defender;
import com.pokechess.view.components.BattleScreen;

public class BattleManager extends Manager {

    private BattleScreen view;

    private Pokemon my_PKM;
    private Pokemon enemy_PKM;

    private String msg1;
    private String msg2;

    public BattleManager(GameManager gameManager, Pokemon PKM1, Pokemon PKM2) {
        super(gameManager);

        this.my_PKM = PKM1;
        this.enemy_PKM = PKM2;
        this.view = new BattleScreen(this);
    }

    private void enemyBattleModule() {
        int command;
        if (this.roll() < 0.50)
            command = 1; // ATTACK
        else
            command = 2; // DEFEND
        this.enemy_PKM.setCommand(command);
    }

    private void battleEngine() {
        String msg1 = "";
        String msg2 = "";
        if (this.my_PKM.getCommand() == this.enemy_PKM.getCommand()) {
            if (this.roll() > 50) {
                this.applyCommand(this.my_PKM, this.enemy_PKM);
                this.applyCommand(this.enemy_PKM, this.my_PKM);
                msg1 = this.msg1;
                msg2 = this.msg2;
            }
            else {
                this.applyCommand(this.enemy_PKM, this.my_PKM);
                this.applyCommand(this.my_PKM, this.enemy_PKM);
                msg1 = this.msg2;
                msg2 = this.msg1;
            }
        }
        else if (this.my_PKM.getCommand() > this.enemy_PKM.getCommand()) {
            this.applyCommand(this.my_PKM, this.enemy_PKM);
            this.applyCommand(this.enemy_PKM, this.my_PKM);
            msg1 = this.msg1;
            msg2 = this.msg2;
        }
        else if (this.my_PKM.getCommand() < this.enemy_PKM.getCommand()) {
            this.applyCommand(this.enemy_PKM, this.my_PKM);
            this.applyCommand(this.my_PKM, this.enemy_PKM);
            msg1 = this.msg2;
            msg2 = this.msg1;
        }
        this.view.getMsgPane().setText(msg1, 1);
        this.view.getMsgPane().setText(msg2, 2);
        this.view.refresh();
    }

    private void applyCommand(Pokemon PKM, Pokemon enemy_PKM) {
        if (PKM.getCommand() == 2) {
            this.defendCommand(PKM);
        }
        else if (PKM.getCommand() == 1) {
            this.attackCommand(PKM, enemy_PKM);
        }
    }

    public void battleCommand(int command) {
        this.msg1 = null;
        this.msg2 = null;
        this.my_PKM.setCommand(command);
        this.enemyBattleModule();
        this.battleEngine();
    }

    private void attackCommand(Pokemon PKM, Pokemon enemy_PKM) {
        int rHP = enemy_PKM.getHealth();
        double aAtk = PKM.getAttack() * 0.01;
        double rDef = enemy_PKM.getDefense() * 0.01;
        double atkDmg = rHP * aAtk;
        double defBuffer = atkDmg * rDef;
        double totalDmg = atkDmg - defBuffer;

        double rNewHP = rHP - totalDmg;

        if (enemy_PKM.getCommand() == 2)
            rNewHP = rHP - (totalDmg * 0.8);

        enemy_PKM.setHealth((int) rNewHP);

        if (enemy_PKM.getHealth() == 0)
            this.endGame(PKM);

        String msg = PKM.getName() + " USED " + PKM.getAttackMove() + " ON " + enemy_PKM.getName();
        this.setMSG(PKM, msg);
    }

    private void defendCommand(Pokemon PKM) {
        String msg = PKM.getName() + " USED " + PKM.getDefenseMove();
        this.setMSG(PKM, msg);
    }

    private void endGame(Pokemon PKM) {
        Pokemon enemy_PKM;
        if (PKM == this.my_PKM)
            enemy_PKM = this.enemy_PKM;
        else
            enemy_PKM = this.my_PKM;

        if (PKM.getPlayer() == 1) {
            if (PKM.getCol() == 5) {
                if (enemy_PKM.getCol() == 6) {
                    this.gameManager.getBoardManager().getPlayer().addTeam_pts(PKM);
                }
            }
        }
        else {
            if (PKM.getCol() == 1) {
                if (enemy_PKM.getCol() == 0) {
                    this.gameManager.getBoardManager().getPlayer().addTeam_pts(PKM);
                }
            }
        }

        if (enemy_PKM.getHomeCol() != enemy_PKM.getCol()) {
            Tile tile = this.gameManager.getBoardManager().getView().getBoard().getTiles()[enemy_PKM.getRow()][enemy_PKM.getCol()];
            PKM.move(tile, enemy_PKM.getRow(), enemy_PKM.getCol());
            PKM.setPoint(PKM.getPoint() + 1);
        }

        this.isDead(enemy_PKM);
        this.gameManager.getBoardManager().getView().display();

        if (this.gameManager.getBoardManager().getView().getBoard().hasGoalDef(PKM))
            if (enemy_PKM.isDefender())
                if (enemy_PKM.getHomeCol() == enemy_PKM.getCol())
                    this.openBattleScreen(PKM, this.gameManager.getBoardManager().getView().getBoard().getGoalDef(PKM));
                else
                    this.openBoardScreen(this.gameManager.getBoardManager());
            else
                this.openBoardScreen(this.gameManager.getBoardManager());
        else
            this.openBoardScreen(this.gameManager.getBoardManager());

    }

    private void isDead(Pokemon PKM) {
        PKM.setRev_turn(this.gameManager.getBoardManager().getTurn() + PKM.getRev());
        PKM.setIsAlive(false);
        PKM.setTile(null);
    }

    private void setMSG(Pokemon PKM, String msg) {
        if (PKM.getPlayer() == 1) {
            this.msg1 = msg;
        }
        else {
            this.msg2 = msg;
        }
    }

    private double roll() {
        return Math.random();
    }

    public Pokemon getMy_PKM() {
        return this.my_PKM;
    }

    public Pokemon getEnemy_PKM() {
        return this.enemy_PKM;
    }

    public BattleScreen getView() {
        return this.view;
    }
}
