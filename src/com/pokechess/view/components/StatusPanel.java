package com.pokechess.view.components;

import com.pokechess.model.characters.Pokemon;
import com.pokechess.model.loaders.Colors;

import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel {

    private Pokemon PKM;

    private JPanel rawHealthBar;

    public StatusPanel(Pokemon PKM) {
        this.setVisible(true);
        this.setLayout(null);
        this.setSize(250, 100);
        this.setBackground(Color.ORANGE);

        this.PKM = PKM;

        this.initComponents();
    }

    private void initComponents() {

        this.displayPKMName();

        this.rawHealthBar = new JPanel();
        this.rawHealthBar.setVisible(true);
        this.rawHealthBar.setLocation(50, 50);
        this.rawHealthBar.setLayout(null);

        this.displayRawHealthBar();

        this.add(this.rawHealthBar);
        this.displayEmptyHealthBar();
    }

    public void display() {
        this.displayRawHealthBar();
    }

    private void displayPKMName() {
        JLabel name = new JLabel(this.PKM.getName());
        name.setVisible(true);
        name.setOpaque(false);
        name.setSize(100, 50);
        name.setLocation(50, 0);

        this.add(name);
    }

    private void displayRawHealthBar() {

        this.rawHealthBar.setSize((int) (200 * ((double) this.PKM.getHealth() / this.PKM.getMaxHealth())), 20);

        if (this.PKM.getHealth() > PKM.getMaxHealth() * 0.50)
            rawHealthBar.setBackground(Color.GREEN);
        else if (this.PKM.getHealth() <= PKM.getMaxHealth() * 0.50 && PKM.getHealth() > PKM.getMaxHealth() * 0.20)
            rawHealthBar.setBackground(Color.YELLOW);
        else if (this.PKM.getHealth() <= PKM.getMaxHealth() * 0.20)
            rawHealthBar.setBackground(Color.RED);
        else
            rawHealthBar.setBackground(Colors.TRANSPARENT);
    }

    private void displayEmptyHealthBar() {
        JPanel emptyBar = new JPanel();
        emptyBar.setVisible(true);
        emptyBar.setSize(200, 20);
        emptyBar.setLocation(50, 50);
        emptyBar.setLayout(null);
        emptyBar.setBackground(Color.GRAY);

        this.add(emptyBar);
    }
}
