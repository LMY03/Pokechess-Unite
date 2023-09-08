package com.pokechess.model.board;

import com.pokechess.model.characters.Pokemon;
import com.pokechess.view.components.Panel;

import java.awt.*;
import java.awt.event.MouseListener;

public class Board extends Panel {

    private Tile[][] tiles;

    public Board(Dimension size, Pokemon[] my_PKMs, Pokemon[] enemy_PKMs, MouseListener listener) {
        super(size);

        this.initializeTiles(listener);
        this.initializePKMs(my_PKMs);
        this.initializePKMs(enemy_PKMs);

        this.initComponents();

        this.add(this.background);
    }

    private void initComponents() {
        this.setBG("board.png", this.getWidth());
        this.displayBoard();
    }

    public void displayBoard() {
        for (int row = 0; row < this.tiles.length; row++) {
            for (int col = 0; col < this.tiles[0].length; col++) {
                Tile tile = this.tiles[row][col];

                tile.setOpaque(false);
                tile.setBackground(null);
                tile.setIcon(null);

                if (tile.hasPKM())
                    if (tile.getPKM().isAlive())
                        tile.displayPKM();

                int size = 120;
                int borderX = 26;
                int borderY = 25;

                int x = ((size + 9) * col) + borderX;
                int y = ((size + 8) * row) + borderY;

                tile.setLocation(x, y);
                tile.setBackground(Color.WHITE);
            }
        }
    }

    private void initializeTiles(MouseListener listener) {
        this.tiles = new Tile[5][7];
        for (int row = 0; row < this.tiles.length; row++)
            for (int col = 0; col < this.tiles[0].length; col++) {
                this.tiles[row][col] = new Tile(listener);
                this.add(this.tiles[row][col]);
            }
    }

    private void initializePKMs(Pokemon[] PKMs) {
        for (int i = 0; i < PKMs.length; i++)
            this.tiles[i][PKMs[i].getHomeCol()].place(PKMs[i]);
    }

    public boolean hasPKM(Tile tile) {
        return tile.getPKM() != null;
    }

    public boolean hasGoalDef(Pokemon PKM) {
        int enemyCol;
        if (PKM.getPlayer() == 1)
            enemyCol = 6;
        else
            enemyCol = 0;
        for (int i = 0; i < tiles.length; i ++)
            if (this.hasPKM(this.tiles[i][enemyCol]))
                if (this.tiles[i][enemyCol].getPKM().isAlive())
                    if (this.tiles[i][enemyCol].getPKM().isDefender())
                        return true;
        return false;
    }

    public Pokemon getGoalDef(Pokemon PKM) {
        int enemyCol;
        if (PKM.getPlayer() == 1)
            enemyCol = 6;
        else
            enemyCol = 0;
        for (int i = 0; i < tiles.length; i ++)
            if (this.hasPKM(this.tiles[i][enemyCol]))
                if (this.tiles[i][enemyCol].getPKM().isDefender())
                    return this.tiles[i][enemyCol].getPKM();
        return null;
    }

    public Tile[][] getTiles() {
        return this.tiles;
    }
}
