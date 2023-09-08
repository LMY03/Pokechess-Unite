package com.pokechess.model.characters.battletype;

import com.pokechess.model.characters.Pokemon;

public class Defender extends Pokemon {

    public Defender(String name, String attackMove, String defenseMove) {
        super(name, attackMove, defenseMove);
        this.health = 100;
        this.maxHealth = health;
        this.attack = 20;
        this.defense = 25;
        this.speed = 1;
        this.gen = 5;
        this.rev = 4;
    }
}
