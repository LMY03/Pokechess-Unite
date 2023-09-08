package com.pokechess.view.components;

import com.pokechess.model.loaders.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class Button extends JButton {

    private JLabel text;

    public Button(MouseListener listener) {

        this.setVisible(true);
        this.setContentAreaFilled(false);
        this.setLayout(null);
        this.setBorder(null);
        this.setOpaque(false);
        this.setMargin(new Insets(0, 0, 0, 0));

        this.addMouseListener(listener);
    }

    @Override
    public void setText(String name) {
        this.text = new JLabel();
        this.text.setVisible(true);
        this.text.setOpaque(false);
        this.text.setText(name);
        this.text.setSize(this.getSize());
        this.text.setHorizontalAlignment(JLabel.HORIZONTAL);
        this.text.setFont(new Font("Monospaced", Font.PLAIN, 25));

        this.add(this.text);
    }

    public void setBtnImage(String path, int width) {
        ImageIcon icon = ImageLoader.loadImageIcon(path, width);
        this.setIcon(icon);
        this.setSize(this.getIcon().getIconWidth(), this.getIcon().getIconHeight());
    }

    public void setBtnImage(int width) {
        String path = ImageLoader.BTN + "btn.png";
        this.setBtnImage(path, width);
    }

    public void setBtnSelected() {
        String path = ImageLoader.BTN + "btn-selected.png";
        this.setBtnImage(path, this.getWidth());
    }

    public void setBtnUnselect() {
        String path = ImageLoader.BTN + "btn.png";
        this.setBtnImage(path, this.getWidth());
    }

    public JLabel gettext() {
        return this.text;
    }
}
