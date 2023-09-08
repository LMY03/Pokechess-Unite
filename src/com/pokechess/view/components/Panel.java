package com.pokechess.view.components;

import com.pokechess.model.loaders.ImageLoader;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    protected JLabel background;

    public Panel(Dimension size) {
        this.setVisible(true);
        this.setSize(size);
        this.setLayout(null);
    }

    protected void setBG(String path) {
        this.setBG(path, this.getWidth());
        this.background = new JLabel();
        this.background.setVisible(true);
        ImageIcon icon = ImageLoader.loadImageIcon(ImageLoader.BG + path);
        this.background.setIcon(icon);
        this.background.setSize(icon.getIconWidth(), icon.getIconHeight());
    }

    protected void setBG(String path, int width) {
        this.background = new JLabel();
        this.background.setVisible(true);
        ImageIcon icon = ImageLoader.loadImageIcon(ImageLoader.BG + path, width);
        this.background.setIcon(icon);
        this.background.setSize(icon.getIconWidth(), icon.getIconHeight());
    }
}
