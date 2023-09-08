package com.pokechess.view.components;

import com.pokechess.controller.Manager;

public abstract class Screen extends Panel {

    protected Screen(Manager programManager) {
        super(programManager.getGameManager().getFrame().getSize());

        this.initComponents(programManager);
    }

    protected abstract void initComponents(Manager programManager);

    protected abstract void processMouseClick(Object source);

    protected abstract void processMouseEntered(Object source);

    protected abstract void processMouseExited(Object source);
}
