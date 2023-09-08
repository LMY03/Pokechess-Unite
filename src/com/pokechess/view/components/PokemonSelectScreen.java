package com.pokechess.view.components;

import com.pokechess.controller.BoardManager;
import com.pokechess.controller.Manager;
import com.pokechess.controller.PokemonSelectManager;
import com.pokechess.model.characters.Pokemon;
import com.pokechess.model.loaders.Guide;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PokemonSelectScreen extends Screen implements MouseListener {

    private PokemonSelectManager programManager;
    private ScrollablePanel scrollablePanel;

    private PokemonCard[] pokemonCards;

    private Button confirmBtn;

    public PokemonSelectScreen(PokemonSelectManager programManager) {
        super(programManager);

        this.generateRoster();

        this.add(this.confirmBtn);
        this.add(this.background);
    }

    @Override
    protected void initComponents(Manager programManager) {

        this.programManager = (PokemonSelectManager) programManager;
        this.setBG("pokemon-select-screen.png", this.getWidth());

        this.scrollablePanel = new ScrollablePanel(this.getWidth() - 80, 150);
        Guide.centerHorizontally(this, this.scrollablePanel);
        this.scrollablePanel.setLocation(this.scrollablePanel.getX(), 500);

        this.pokemonCards = new PokemonCard[25];

        this.confirmBtn = new Button(this);
        this.confirmBtn.setVisible(false);
        this.confirmBtn.setBtnImage(290);
        this.confirmBtn.setText("CONFIRM");

        this.add(this.scrollablePanel);
    }

    private void generateRoster() {
        Pokemon[] PKM_list = this.programManager.getPKM_list();
        for (int i = 0; i < PKM_list.length; i++) {
            String name = PKM_list[i].getName();
            this.pokemonCards[i] = new PokemonCard(PKM_list[i], name,this);
            this.scrollablePanel.addItem(this.pokemonCards[i]);
        }
    }

    @Override
    protected void processMouseClick(Object source) {
        this.programManager.choosePKM(source);
        if (this.programManager.isComplete()) {
            this.confirmBtn.setVisible(true);
            if (source == this.confirmBtn) {
                this.programManager.enemyPKMs();
                this.programManager.openBoardScreen(new BoardManager(this.programManager.getGameManager(),
                        this.programManager.getMy_PKMs(), this.programManager.getEnemy_PKMs()));
            }
        }
        else
            this.confirmBtn.setVisible(false);
    }

    @Override
    protected void processMouseEntered(Object source) {
        if (source == this.confirmBtn)
            this.confirmBtn.setBtnSelected();
        for (int i = 0; i < this.pokemonCards.length; i++)
            if (source == this.pokemonCards[i])
                if (!this.pokemonCards[i].getIsSelected())
                    this.pokemonCards[i].setBtnSelected();
    }

    @Override
    protected void processMouseExited(Object source) {
        if (source == this.confirmBtn)
            this.confirmBtn.setBtnUnselect();
        for (int i = 0; i < this.pokemonCards.length; i++)
            if (source == this.pokemonCards[i])
                if (!this.pokemonCards[i].getIsSelected())
                    this.pokemonCards[i].setBtnUnselect();
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

    public PokemonCard[] getPokemonCards() {
        return pokemonCards;
    }
}
