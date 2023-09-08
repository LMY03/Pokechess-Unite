package com.pokechess.controller;

import com.pokechess.view.components.TitleScreen;

public class TitleManager extends Manager {

    public TitleManager(GameManager gameManager) {
        super(gameManager);
        this.view = new TitleScreen(this);
    }
}