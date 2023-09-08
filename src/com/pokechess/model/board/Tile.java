package com.pokechess.model.board;

import com.pokechess.model.characters.Pokemon;
import com.pokechess.model.loaders.ImageLoader;
import com.pokechess.view.components.Button;

import java.awt.event.MouseListener;

public class Tile extends Button {

    private Pokemon PKM;

    public Tile(MouseListener listener) {
        super(listener);

        this.setSize(120, 120);
    }

    public void displayPKM() {
        String path = ImageLoader.PKM_FULL + this.PKM.getName().toLowerCase() + ".png";
        this.setBtnImage(path, this.getWidth());
    }

    public void place(Pokemon PKM) {
        this.setPKM(PKM);
        if (this.PKM.getTile() != null) {
            this.PKM.getTile().removePKM();
        }
        if (this.PKM != null) {
            this.PKM.setTile(this);
        }
    }

    public boolean hasPKM() {
        return this.PKM != null;
    }

    public boolean hasEnemy(Pokemon PKM) {
        return this.PKM.getPlayer() != PKM.getPlayer();
    }

    public void removePKM() {
        this.PKM = null;
    }

    public Pokemon getPKM() {
        return this.PKM;
    }

    public void setPKM(Pokemon PKM) {
        this.PKM = PKM;
    }
}
