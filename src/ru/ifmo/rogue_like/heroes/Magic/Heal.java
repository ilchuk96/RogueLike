package ru.ifmo.rogue_like.heroes.Magic;

import ru.ifmo.rogue_like.heroes.mobs.Hero;
import ru.ifmo.rogue_like.map.IMap;

public class Heal extends Magic {

    private int heal;

    public Heal() {
        heal = 1;
        mana = 5;
        needExp = 10;
    }

    @Override
    public void apply(IMap map, Hero hero) {
        hero.getDamage(-heal);
    }

    @Override
    public String info() {
        return "Heal yourself with " + heal + ", requires " + mana;
    }

    @Override
    public String getLUInfo() {
        if (canLevelUp()) {
            return "-1 to mana for " + needExp + " exp";
        }
        return "Max Level";
    }

    @Override
    protected void upgrade() {
        mana--;
        needExp *= 10;
    }

    @Override
    public boolean canLevelUp() {
        return mana != 1;
    }
}
