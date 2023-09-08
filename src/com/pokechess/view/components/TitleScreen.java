package com.pokechess.view.components;

import com.pokechess.controller.Manager;
import com.pokechess.controller.TitleManager;
import com.pokechess.model.loaders.Guide;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TitleScreen extends Screen implements MouseListener {

    private TitleManager programManager;

    private Button startBtn;

    public TitleScreen(TitleManager programManager) {
        super(programManager);

        this.add(this.startBtn);
        this.add(this.background);
    }

    @Override
    protected void initComponents(Manager programManager) {

        this.programManager = (TitleManager) programManager;
        this.setBG("title-screen.jpg");

        this.startBtn = new Button(this);
        this.startBtn.setBtnImage(290);
        Guide.centerHorizontally(this, this.startBtn);
        this.startBtn.setLocation(this.startBtn.getX(), 400);
        this.startBtn.setText("START");
    }

    @Override
    protected void processMouseClick(Object source) {
        if (source == this.startBtn)
            this.programManager.openPokemonSelectScreen();
    }

    @Override
    protected void processMouseEntered(Object source) {
        if (source == this.startBtn)
            this.startBtn.setBtnSelected();
    }

    @Override
    protected void processMouseExited(Object source) {
        if (source == this.startBtn)
            this.startBtn.setBtnUnselect();
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
