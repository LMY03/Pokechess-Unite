package com.pokechess.view.components;

import com.pokechess.controller.BoardManager;
import com.pokechess.controller.Manager;
import com.pokechess.model.board.Board;
import com.pokechess.model.loaders.Guide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BoardScreen extends Screen implements MouseListener {

    private BoardManager programManager;

    private Board board;

    private JLabel playerTurn;
    private JLabel nTurn;

    private JLabel my_TeamPts;
    private JLabel enemy_TeamPts;

    private Button endTurnBtn;

    public BoardScreen(BoardManager programManager) {
        super(programManager);

        this.add(this.playerTurn);
        this.add(this.nTurn);
        this.add(this.my_TeamPts);
        this.add(this.enemy_TeamPts);
        this.add(this.board);
        this.add(this.endTurnBtn);
        this.add(this.background);
    }

    @Override
    protected void initComponents(Manager programManager) {

        this.programManager = (BoardManager) programManager;
        this.setBG("board-screen.jpg");

        this.board = new Board(new Dimension(946, this.getHeight()),
                this.programManager.getPlayer().getPKMs(), this.programManager.getEnemy().getPKMs(), this);

        this.nTurn = this.setLabel(1050, -100);
        this.nTurn.setText("TURN " + this.programManager.getTurn());

        this.playerTurn = this.setLabel(1000, 0);

        this.my_TeamPts = this.setLabel(975, 100);
        this.my_TeamPts.setText("MY TEAM POINTS " + this.programManager.getPlayer().getTeam_pts());

        this.enemy_TeamPts = this.setLabel(965, 200);
        this.enemy_TeamPts.setText("ENEMY TEAM POINTS " + this.programManager.getPlayer().getTeam_pts());

        this.endTurnBtn = new Button(this);
        this.endTurnBtn.setBtnImage(250);
        this.endTurnBtn.setLocation(975, 500);
        this.endTurnBtn.setText("END TURN");
    }

    private JLabel setLabel(int x, int y) {
        JLabel label = new JLabel();
        label.setVisible(true);
        label.setOpaque(false);
        label.setLocation(x, y);
        label.setSize(500, 500);
        label.setFont(new Font("Monospaced", Font.PLAIN, 25));
        return label;
    }

    public void display() {
        this.board.displayBoard();
        this.nTurn.setText("TURN " + this.programManager.getTurn());
        this.my_TeamPts.setText("MY TEAM POINTS " + this.programManager.getPlayer().getTeam_pts());
        this.enemy_TeamPts.setText("ENEMY TEAM POINTS " + this.programManager.getPlayer().getTeam_pts());
        this.revalidate();
        this.repaint();
    }

    @Override
    protected void processMouseClick(Object source) {
        if (this.endTurnBtn == source) {
            this.programManager.actionPerformed();
        }
        if (this.programManager.getSelectedPKM() != null) {
            this.programManager.selectPKM(source);
            this.programManager.boardCommand(source);
        }
        else
            this.programManager.selectPKM(source);
    }

    @Override
    protected void processMouseEntered(Object source) {
        if (source == this.endTurnBtn)
            this.endTurnBtn.setBtnSelected();
    }

    @Override
    protected void processMouseExited(Object source) {
        if (source == this.endTurnBtn)
            this.endTurnBtn.setBtnUnselect();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.processMouseClick(e.getSource());
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.processMouseEntered(e.getSource());
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.processMouseExited(e.getSource());
    }

    public Board getBoard() {
        return this.board;
    }

    public JLabel getPlayerTurn() {
        return this.playerTurn;
    }
}
