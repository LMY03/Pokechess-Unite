package com.pokechess.view.components;

import com.pokechess.model.loaders.Colors;

import javax.swing.*;
import java.awt.*;

public class MessagePane extends JPanel {

    private JLabel msg1;
    private JLabel msg2;

    public MessagePane(Dimension size) {
        this.setVisible(true);
        this.setLayout(null);
        this.setSize(size);
        this.setBackground(Color.ORANGE);
        this.setBorder(BorderFactory.createLineBorder(Colors.OVERLAY_BLACK, 5));

        this.initComponent();

        this.add(this.msg1);
        this.add(this.msg2);
    }

    private void initComponent() {
        this.msg1 = new JLabel();
        this.msg1.setVisible(true);
        this.msg1.setOpaque(false);
        this.msg1.setLocation(50, 10);
        this.msg1.setSize(this.getWidth() / 2, this.getHeight() / 2);
        this.msg1.setFont(new Font("Monospaced", Font.PLAIN, 25));

        this.msg2 = new JLabel();
        this.msg2.setVisible(true);
        this.msg2.setOpaque(false);
        this.msg2.setLocation(50, 50);
        this.msg2.setSize(this.getWidth() / 2, this.getHeight() / 2);
        this.msg2.setFont(new Font("Monospaced", Font.PLAIN, 25));
    }

    public void setText(String msg, int n) {
        if (n == 1)
            this.msg1.setText(msg);
        else if (n == 2)
            this.msg2.setText(msg);
    }
}
