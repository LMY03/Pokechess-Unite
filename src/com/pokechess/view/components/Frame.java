package com.pokechess.view.components;

import com.pokechess.model.loaders.ImageLoader;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    private Screen screen;

    public Frame(int width, int height) {
        super();

        this.loadIcon();
        this.setResizable(false);
        this.setVisible(true);

        this.setSize(width, height);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

//        this.getContentPane().setBackground(Color.BLACK);
    }

    private void loadIcon() {
        String path = ImageLoader.ICON;
        Image image = ImageLoader.loadImage(path);
        this.setIconImage(image);
    }

    public void setScreen(Screen screen) {

        if (this.screen != null) {
            this.remove(this.screen);
        }

        this.screen = screen;
        this.add(this.screen);

        this.repaint();
        this.revalidate();
    }
}
