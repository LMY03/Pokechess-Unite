package com.pokechess.view.components;

import com.pokechess.model.characters.Pokemon;
import com.pokechess.model.loaders.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class PokemonCard extends Button {

    private Pokemon PKM;

    private ImageIcon selected;
    private ImageIcon unselect;

    private boolean isSelected;

    public PokemonCard(Pokemon PKM, String name, MouseListener listener) {
        super(listener);
        this.PKM = PKM;
        this.isSelected = false;
        this.setBtnImage(name);
        this.unselect = (ImageIcon) this.getIcon();
        this.selected = this.setGray();
    }

    private void setBtnImage(String name) {
        String path = ImageLoader.PKM_FRAME + name.toLowerCase() + ".png";
        this.setBtnImage(path, 130);
    }

    @Override
    public void setBtnSelected() {
        String path = ImageLoader.PKM_FRAME + PKM.getName().toLowerCase() + "-selected.png";
        this.setBtnImage(path, this.getWidth());
    }

    @Override
    public void setBtnUnselect() {
        String path = ImageLoader.PKM_FRAME + PKM.getName().toLowerCase() + ".png";
        ImageIcon icon = ImageLoader.loadImageIcon(path, this.getWidth());
        this.setIcon(icon);
        this.setSize(this.getIcon().getIconWidth(), this.getIcon().getIconHeight());
    }

    private ImageIcon setGray() {
        final int w = this.getIcon().getIconWidth();
        final int h = this.getIcon().getIconHeight();
        GraphicsEnvironment ge =
                GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gd.getDefaultConfiguration();
        BufferedImage image = gc.createCompatibleImage(w, h);
        Graphics2D g2d = image.createGraphics();
        this.getIcon().paintIcon(null, g2d, 0, 0);
        Image gray = GrayFilter.createDisabledImage(image);
        return new ImageIcon(gray);
    }

    public Pokemon getPKM() {
        return this.PKM;
    }

    public ImageIcon getSelected() {
        return this.selected;
    }

    public ImageIcon getUnselect() {
        return this.unselect;
    }

    public boolean getIsSelected() {
        return this.isSelected;
    }

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }
}
