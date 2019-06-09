package ru.ifmo.rogue_like.heroes.magic;

import ru.ifmo.rogue_like.heroes.IHeroesService;
import ru.ifmo.rogue_like.heroes.mobs.Hero;

public class Heal extends Magic {

    private int heal;

    public Heal() {
        heal = 1;
        mana = 5;
        needExp = 10;
    }

    @Override
    public void apply(IHeroesService heroesService, Hero hero) {
        hero.getDamage(-heal);
    }

    @Override
    public String info() {
        return "Heal yourself with " + heal + ", requires " + mana;
    }

    @Override
    protected void upgrade() {
        mana--;
        needExp *= 10;
    }

    @Override
    public boolean canLevelUp() {
        return mana == 1;
    }
}
