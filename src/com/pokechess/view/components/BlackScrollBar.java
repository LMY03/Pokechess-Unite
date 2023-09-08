package com.pokechess.view.components;

import com.pokechess.model.loaders.Colors;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class BlackScrollBar extends BasicScrollBarUI {

    @Override
    protected JButton createDecreaseButton(int orientation) {
        super.createDecreaseButton(orientation);

        JButton btnDecrease = new JButton();
        btnDecrease.setBorder(null);
        btnDecrease.setContentAreaFilled(false);
        return btnDecrease;
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        super.createIncreaseButton(orientation);

        JButton btnIncrease = new JButton();
        btnIncrease.setBorder(null);
        btnIncrease.setContentAreaFilled(false);
        return btnIncrease;
    }

    @Override
    protected void configureScrollBarColors() {
//        super.configureScrollBarColors();

        this.thumbColor = Colors.VOILET;
        this.trackColor = Colors.OVERLAY_BLACK;
    }
}
