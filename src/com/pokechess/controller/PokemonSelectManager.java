package com.pokechess.controller;

import com.pokechess.view.components.PokemonCard;
import com.pokechess.model.characters.Pokemon;
import com.pokechess.model.characters.battletype.*;
import com.pokechess.view.components.PokemonSelectScreen;

public class PokemonSelectManager extends Manager {

    private PokemonSelectScreen view;

    private Pokemon[] my_PKMs;
    private Pokemon[] enemy_PKMs;

    private Pokemon[] PKM_list;

    private int pkm;

    public PokemonSelectManager(GameManager gameManager) {
        super(gameManager);

        this.my_PKMs = new Pokemon[5];
        this.enemy_PKMs = new Pokemon[5];
        this.initPKMs();
        this.view = new PokemonSelectScreen(this);
    }

    private void initPKMs() {

        this.pkm = 0;
        this.PKM_list = new Pokemon[25];

        this.PKM_list[0] = new Attacker("CINDERACE", "BLAZE KICK", "FIENT");
        this.PKM_list[1] = new All_Rounder("GARCHOMP", "DRAGON RUSH", "DIG");
        this.PKM_list[2] = new Speedster("GENGAR", "SLUDGE BOMB", "HEX");
        this.PKM_list[3] = new Attacker("CRAMORANT", "SURF", "DIVE");
        this.PKM_list[4] = new Attacker("NINETALES", "BLIZZARD", "AURORA VEIL");
        this.PKM_list[5] = new Supporter("WIGGLYTUFF", "DOUBLE SLAP", "SING");
        this.PKM_list[6] = new All_Rounder("MACHAMP", "CLOSE COMBAT", "BULK UP");
        this.PKM_list[7] = new Speedster("ABSOL", "PURSUIT", "FIENT");
        this.PKM_list[8] = new Defender("SLOWBRO", "SURF", "TELEKENISIS");
        this.PKM_list[9] = new Supporter("MRMIME", "CONFUSION", "BARRIER");
        this.PKM_list[10] = new Attacker("VENUSAUR", "SOLAR BEAM", "OVERGROW");
        this.PKM_list[11] = new All_Rounder("LUCARIO", "EXTREME SPEED", "BONE RUSH");
        this.PKM_list[12] = new Speedster("TALONFLAME", "BRAVE BIRD", "FLY");
        this.PKM_list[13] = new Supporter("ELDEGOSS", "LEAF TORNADO", "COTTON GUARD");
        this.PKM_list[14] = new Attacker("GRENINJA", "SURF", "DOUBLE TEAM");
        this.PKM_list[15] = new Defender("CRUSTLE", "X-SCISSOR", "SHELL SMASH");
        this.PKM_list[16] = new Defender("SNORLAX", "HEAVY SLAM", "BLOCK");
        this.PKM_list[17] = new All_Rounder("CHARIZARD", "FLARE BLITZ", "BLAZE");
        this.PKM_list[18] = new Attacker("PIKACHU","THUNDER", "STATIC");
        this.PKM_list[19] = new Speedster("ZERAORA", "DISCHARGE", "VOLT SWITCH");
        this.PKM_list[20] = new Attacker("GARDEVOIR", "MOONBLAST", "TELEPORT");
        this.PKM_list[21] = new Supporter("BLISSEY", "EGG BOMB", "SOFT BOILED");
        this.PKM_list[22] = new Defender("BLASTOISE", "HYDROPUMP", "RAPID SPIN");
        this.PKM_list[23] = new Defender("MAMOSWINE", "EARTHQUAKE", "ICE FANG");
        this.PKM_list[24] = new Attacker("SYLVEON", "MYSTICAL FIRE", "CALM MIND");
    }

    public void choosePKM(Object source) {
        PokemonCard[] PKM_list = this.view.getPokemonCards();
        for (int i = 0; i < PKM_list.length; i++) {
            if (source == PKM_list[i]) {
                if (!PKM_list[i].getIsSelected()) {
                    Pokemon PKM = PKM_list[i].getPKM();
                        if (!this.hasPokemon(this.my_PKMs, PKM)) {
                            if (!this.typeLimit(this.my_PKMs, PKM)) {
                                if (!this.isComplete()) {
                                    PKM_list[i].setIcon(PKM_list[i].getSelected());
                                    PKM_list[i].setIsSelected(true);
                                    PKM.setPKM(1, this.pkm, 0);
                                    this.my_PKMs[this.pkm] = PKM;
                                    this.pkm++;
                                }
                            }
                        }
                }
                else {
                    this.unselectPKM(this.my_PKMs, PKM_list[i]);
                }
            }
        }
    }

    public void enemyPKMs() {
        this.pkm = 0;
        do {
            int i = (int) ((Math.random() * 100) / (100 / this.PKM_list.length));
            Pokemon PKM = this.PKM_list[i];
            if (!this.hasPokemon(this.my_PKMs, PKM)) {
                if (!this.hasPokemon(this.enemy_PKMs, PKM)) {
                    if (!this.typeLimit(this.enemy_PKMs, PKM)) {
                        PKM.setPKM(2, this.pkm, 6);
                        this.enemy_PKMs[this.pkm] = PKM;
                        this.pkm++;
                    }
                }
            }
        } while (this.pkm < 5);
        this.pkm = 0;
    }

    private void unselectPKM(Pokemon[] PKMs, PokemonCard card_PKM) {
        for (int i = 0; i < this.pkm; i++)
            if (PKMs[i] == card_PKM.getPKM())
                if (i + 1 < 5) {
                    if (PKMs[i + 1] != null)
                        PKMs[i] = PKMs[i + 1];
                    else
                        PKMs[i] = null;
                }
        card_PKM.setIcon(card_PKM.getUnselect());
        card_PKM.setIsSelected(false);
        this.pkm--;
    }

    private boolean hasPokemon(Pokemon[] PKMs, Pokemon PKM) {
        if (this.pkm != 0)
            for (int i = 0; i < PKMs.length; i++)
                if (PKMs[i] != null)
                    if (PKMs[i] == PKM)
                        return true;
        return false;
    }

    private boolean typeLimit(Pokemon[] PKMs, Pokemon PKM) {
        if (this.hasAttackerLimit(PKMs, PKM))
            return true;
        else if (this.hasSpeedsterLimit(PKMs, PKM))
            return true;
        else if (this.hasAllRounderLimit(PKMs, PKM))
            return true;
        else if (this.hasDefenderLimit(PKMs, PKM))
            return true;
        else if (this.hasSupporterLimit(PKMs, PKM))
            return true;
        return false;
    }

    private boolean hasAttackerLimit(Pokemon[] PKMs, Pokemon PKM) {
        int atk = 0;
        for (int i = 0; i < this.pkm; i++)
            if (PKMs[i].isAttacker())
                atk++;
        return PKM.isAttacker() && atk > 1;
    }

    private boolean hasSpeedsterLimit(Pokemon[] PKMs, Pokemon PKM) {
        int spd = 0;
        for (int i = 0; i < this.pkm; i++)
            if (PKMs[i].isSpeedster())
                spd++;
        return PKM.isSpeedster() && spd > 1;
    }

    private boolean hasAllRounderLimit(Pokemon[] PKMs, Pokemon PKM) {
        int all = 0;
        for (int i = 0; i < this.pkm; i++)
            if (PKMs[i].isAllRounder())
                all++;
        return PKM.isAllRounder() && all > 1;
    }

    private boolean hasDefenderLimit(Pokemon[] PKMs, Pokemon PKM) {
        int def = 0;
        for (int i = 0; i < this.pkm; i++)
            if (PKMs[i].isDefender())
                def++;
        return PKM.isDefender() && def > 1;
    }

    private boolean hasSupporterLimit(Pokemon[] PKMs, Pokemon PKM) {
        int spt = 0;
        for (int i = 0; i < this.pkm; i++)
            if (PKMs[i].isSupporter())
                spt++;
        return PKM.isSupporter() && spt > 1;
    }

    public boolean isComplete() {
        return this.pkm == 5;
    }

    public Pokemon[] getPKM_list() {
        return this.PKM_list;
    }

    public Pokemon[] getMy_PKMs() {
        return this.my_PKMs;
    }

    public Pokemon[] getEnemy_PKMs() {
        return this.enemy_PKMs;
    }

    @Override
    public PokemonSelectScreen getView() {
        return this.view;
    }
}
