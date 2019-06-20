package ru.ifmo.roguelike.heroes.magic;

import ru.ifmo.roguelike.heroes.IHeroesService;
import ru.ifmo.roguelike.heroes.mobs.IHero;

/**
 * Extend this to add spell
 */
public abstract class Magic {

    private int mana;

    private int needExp;

    protected int getMana() {
        return mana;
    }

    protected int getNeedExp() {
        return needExp;
    }

    protected void setMana(int mana) {
        this.mana = mana;
    }

    protected void setNeedExp(int needExp) {
        this.needExp = needExp;
    }

    public boolean cast(IHeroesService heroService, IHero hero) {
        if (hero.getMana() < mana) {
            return false;
        }
        hero.reduceMana(mana);
        apply(heroService, hero);
        return true;
    }

    abstract protected void apply(IHeroesService heroService, IHero hero);

    int needMana() {
        return mana;
    }

    public abstract String info();

    public abstract String getLUInfo();

    public boolean levelUp(IHero hero) {
        if (hero.getExp() < needExp && !canLevelUp()) {
            return false;
        }
        hero.changeExpBy(-needExp);
        upgrade();
        return true;
    }

    abstract protected void upgrade();

    public int needExpToLevelUp() {
        return needExp;
    }

    public abstract boolean canLevelUp();

}
