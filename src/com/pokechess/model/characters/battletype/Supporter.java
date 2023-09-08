package com.pokechess.model.characters.battletype;

import com.pokechess.model.characters.Pokemon;

public class Supporter extends Pokemon {

    public Supporter(String name, String attackMove, String defenseMove) {
        super(name, attackMove, defenseMove);
        this.health = 100;
        this.maxHealth = health;
        this.attack = 20;
        this.defense = 5;
        this.speed = 1;
        this.gen = 15;
        this.rev = 3;
    }

    public void heal(Pokemon PKM) {
        PKM.setHealth((int) (this.getTile().getPKM().getHealth() + this.getTile().getPKM().getHealth() * 0.20));
    }
}
