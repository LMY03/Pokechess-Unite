package com.pokechess.model.characters.battletype;

import com.pokechess.model.characters.Pokemon;

public class All_Rounder extends Pokemon {

    public All_Rounder(String name, String attackMove, String defenseMove) {
        super(name, attackMove, defenseMove);
        this.health = 75;
        this.maxHealth = health;
        this.attack = 30;
        this.defense = 15;
        this.speed = 2;
        this.gen = 10;
        this.rev = 3;
    }
}

