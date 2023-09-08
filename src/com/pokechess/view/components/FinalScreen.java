package com.pokechess.view.components;

import com.pokechess.controller.FinalManager;
import com.pokechess.controller.Manager;
import com.pokechess.model.loaders.Guide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FinalScreen extends Screen implements MouseListener {

    private FinalManager programManager;

    private JLabel headLine;

    private Button replayBtn;
    private Button exitBtn;

    public FinalScreen(FinalManager programManager) {
        super(programManager);

        this.add(this.exitBtn);
        this.add(this.replayBtn);
        this.add(this.headLine);
        this.add(this.background);
    }

    @Override
    protected void initComponents(Manager programManager) {

        this.programManager = (FinalManager) programManager;
        this.setBG("final-screen.jpg", this.getWidth());

        this.headLine = new JLabel();
        this.headLine.setVisible(true);
        this.headLine.setOpaque(false);
        this.headLine.setSize(650, 200);
        this.headLine.setFont(new Font("Monospaced", Font.BOLD, 100));
        Guide.centerHorizontally(this, this.headLine);
        this.headLine.setLocation(this.headLine.getX(), 0);

        String text = this.programManager.getGameManager().getBoardManager().getPlayer().getName();
        System.out.println(this.programManager.getGameManager().getBoardManager().getPlayer().isWin());
        System.out.println(this.programManager.getGameManager().getBoardManager().getEnemy().isWin());
        if (this.programManager.getGameManager().getBoardManager().getPlayer().isWin())
            text = text + " WIN";
        else if (this.programManager.getGameManager().getBoardManager().getEnemy().isWin())
            text = text + " LOSE";
        this.headLine.setText(text);

        this.replayBtn = new Button(this);
        this.replayBtn.setBtnImage(290);
        this.replayBtn.setText("REPLAY");
        Guide.centerHorizontally(this, this.replayBtn);
        this.replayBtn.setLocation(this.replayBtn.getX(), 400);

        this.exitBtn = new Button(this);
        this.exitBtn.setBtnImage(290);
        this.exitBtn.setText("EXIT");
        Guide.centerHorizontally(this, this.exitBtn);
        this.exitBtn.setLocation(this.exitBtn.getX(), 500);
    }

    @Override
    protected void processMouseClick(Object source) {
        if (source == this.replayBtn)
            this.programManager.openTitleScreen();
        if (source == this.exitBtn)
            System.exit(0);
    }

    @Override
    protected void processMouseEntered(Object source) {
        if (source == this.replayBtn)
            this.replayBtn.setBtnSelected();
        if (source == this.exitBtn)
            this.exitBtn.setBtnSelected();
    }

    @Override
    protected void processMouseExited(Object source) {
        if (source == this.replayBtn)
            this.replayBtn.setBtnUnselect();
        if (source == this.exitBtn)
            this.exitBtn.setBtnUnselect();
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
}
