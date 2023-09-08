package com.pokechess.model.characters.battletype;

import com.pokechess.model.characters.Pokemon;

public class Attacker extends Pokemon {

    public Attacker(String name, String attackMove, String defenseMove) {
        super(name, attackMove, defenseMove);
        this.health = 75;
        this.maxHealth = health;
        this.attack = 40;
        this.defense = 15;
        this.speed = 2;
        this.gen = 5;
        this.rev = 2;
    }
}