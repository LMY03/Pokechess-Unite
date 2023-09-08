package com.pokechess.controller;

import com.pokechess.model.characters.Pokemon;
import com.pokechess.view.components.Screen;

public abstract class Manager {

    protected GameManager gameManager;
    protected Screen view;

    protected Manager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void openTitleScreen() {
        this.gameManager.openTitleScreen();
    }

    public void openPokemonSelectScreen() {
        this.gameManager.openPokemonSelectScreen();
    }

    public void openBoardScreen(BoardManager boardManager) {
        this.gameManager.openBoardScreen(boardManager);
    }

    public void openBattleScreen(Pokemon my_PKM, Pokemon enemy_PKM) {
        this.gameManager.openBattleScreen(my_PKM, enemy_PKM);
    }

    public void openFinalScreen() {
        this.gameManager.openFinalScreen();
    }

    public GameManager getGameManager() {
        return this.gameManager;
    }

    public Screen getView() {
        return view;
    }
}
