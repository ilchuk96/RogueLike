package ru.ifmo.roguelike.heroes.magic;

import ru.ifmo.roguelike.heroes.IHeroesService;
import ru.ifmo.roguelike.heroes.mobs.IHero;

/**
 * Heal yourself
 */
public class Heal extends Magic {

    private int heal;

    public Heal() {
        heal = 1;
        setMana(5);
        setNeedExp(10);
    }

    @Override
    public void apply(IHeroesService heroesService, IHero hero) {
        hero.changeHPBy(heal);
    }

    @Override
    public String info() {
        return "Heal yourself with " + heal + ", requires " + getMana();
    }

    @Override
    public String getLUInfo() {
        if (canLevelUp()) {
            return "-1 to mana for " + getNeedExp() + " exp";
        }
        return "Max Level";
    }

    @Override
    protected void upgrade() {
        setMana(getMana() - 1);
        setNeedExp(getNeedExp() * 10);
    }

    @Override
    public boolean canLevelUp() {
        return getMana() != 1;
    }
}
