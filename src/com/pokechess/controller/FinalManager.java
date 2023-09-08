package com.pokechess.controller;

import com.pokechess.view.components.FinalScreen;

public class FinalManager extends Manager {

    private FinalScreen view;

    public FinalManager(GameManager gameManager) {
        super(gameManager);

        this.view = new FinalScreen(this);
    }

    @Override
    public FinalScreen getView() {
        return this.view;
    }
}
