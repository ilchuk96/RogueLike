package ru.ifmo.roguelike.heroes.magic;

import ru.ifmo.roguelike.heroes.IHeroesService;
import ru.ifmo.roguelike.heroes.mobs.IHero;

/**
 * Extend this to add spell
 */
public abstract class Magic {

    int mana;

    int needExp;

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
