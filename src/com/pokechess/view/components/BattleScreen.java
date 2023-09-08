package com.pokechess.view.components;

import com.pokechess.controller.BattleManager;
import com.pokechess.controller.Manager;
import com.pokechess.model.loaders.Colors;
import com.pokechess.model.loaders.Guide;
import com.pokechess.model.loaders.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BattleScreen extends Screen implements MouseListener {

    private BattleManager programManager;

    private StatusPanel myStatusPanel;
    private StatusPanel enemyStatusPanel;

    private JLabel my_PKM;
    private JLabel enemy_PKM;

    private MessagePane msgPane;

    private Button atkBtn;
    private Button defBtn;

    public BattleScreen(BattleManager programManager) {
        super(programManager);

        this.add(this.atkBtn);
        this.add(this.defBtn);
        this.add(this.msgPane);
        this.add(this.myStatusPanel);
        this.add(this.enemyStatusPanel);
        this.add(this.my_PKM);
        this.add(this.enemy_PKM);
        this.add(this.background);
    }

    @Override
    protected void initComponents(Manager programManager) {

        this.programManager = (BattleManager) programManager;
        this.setBG("board-screen.jpg");

        this.displayStatusPanel();

        this.my_PKM = this.displayPKM(this.programManager.getMy_PKM().getName(), 750, 250);
        this.enemy_PKM = this.displayPKM(this.programManager.getEnemy_PKM().getName(), 300, 0);

        this.msgPane = new MessagePane(new Dimension(this.getWidth() - 14, 200));
        this.msgPane.setLocation(0, 450);

        this.atkBtn = new Button(this);
        this.defBtn = new Button(this);

        String path = ImageLoader.PKM_MOVE + this.programManager.getMy_PKM().getName().toLowerCase() + "-";

        this.atkBtn.setBtnImage(path + "attack.png", 200);
        this.defBtn.setBtnImage(path + "defend.png", 200);

        this.atkBtn.setLocation(750, 450);
        this.defBtn.setLocation(1000, 450);
    }

    private void displayStatusPanel() {
        this.myStatusPanel = new StatusPanel(this.programManager.getMy_PKM());
        this.enemyStatusPanel = new StatusPanel(this.programManager.getEnemy_PKM());

        this.myStatusPanel.setLocation(1000, 250);
        this.enemyStatusPanel.setLocation(0, 0);
    }

    private JLabel displayPKM(String name, int x, int y) {

        String path = ImageLoader.PKM_FULL + name + ".png";
        ImageIcon imageIcon = ImageLoader.loadImageIcon(path, 300);

        JLabel label = new JLabel(imageIcon);
        label.setVisible(true);
        label.setSize(imageIcon.getIconWidth(),imageIcon.getIconHeight());

        label.setLocation(x, y);

        return label;
    }

    public void refresh() {
        this.myStatusPanel.display();
        this.enemyStatusPanel.display();
        this.revalidate();
        this.repaint();
    }

    @Override
    protected void processMouseClick(Object source) {
        int command = 0;
        if (source == this.atkBtn)
            command = 1;
        else if (source == this.defBtn)
            command = 2;
        this.programManager.battleCommand(command);
    }

    @Override
    protected void processMouseEntered(Object source) {

    }

    @Override
    protected void processMouseExited(Object source) {

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

    public MessagePane getMsgPane() {
        return this.msgPane;
    }
}
