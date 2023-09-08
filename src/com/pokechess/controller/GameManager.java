package com.pokechess.controller;

import com.pokechess.model.characters.Pokemon;
import com.pokechess.model.characters.battletype.Attacker;
import com.pokechess.model.characters.battletype.Defender;
import com.pokechess.view.components.Frame;

public class GameManager {

    private Frame frame;
    private BoardManager boardManager;

    public GameManager() {
        this.frame = new Frame(1280, 720);
    }

    public void run() {
        this.openTitleScreen();
    }

    public void openTitleScreen() {
        TitleManager titleManager = new TitleManager(this);
        this.frame.setScreen(titleManager.getView());
    }

    public void openPokemonSelectScreen() {
        PokemonSelectManager pokemonSelectManager = new PokemonSelectManager(this);
        this.frame.setScreen(pokemonSelectManager.getView());
    }

    public void openBoardScreen(BoardManager boardManager) {
        this.boardManager = boardManager;
        this.frame.setScreen(boardManager.getView());
    }

    public void openBattleScreen(Pokemon PKM1, Pokemon PKM2) {
        BattleManager battleManager = new BattleManager(this, PKM1, PKM2);
        this.frame.setScreen(battleManager.getView());
    }

    public void openFinalScreen() {
        FinalManager finalManager = new FinalManager(this);
        this.frame.setScreen(finalManager.getView());
    }

    public Frame getFrame() {
        return this.frame;
    }

    public BoardManager getBoardManager() {
        return this.boardManager;
    }
}
