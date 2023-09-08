package com.pokechess.model.characters.battletype;

import com.pokechess.model.characters.Pokemon;

public class Speedster extends Pokemon {

    public Speedster(String name, String attackMove, String defenseMove) {
        super(name, attackMove, defenseMove);
        this.health = 50;
        this.maxHealth = health;
        this.attack = 40;
        this.defense = 5;
        this.speed = 3;
        this.gen = 5;
        this.rev = 2;
    }
}
