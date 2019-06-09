package ru.ifmo.rogue_like.heroes.magic;

import ru.ifmo.rogue_like.heroes.IHeroesService;
import ru.ifmo.rogue_like.heroes.mobs.Hero;

public abstract class Magic {

    protected int mana;

    protected int needExp;

    public boolean cast(IHeroesService heroService, Hero hero) {
        if (hero.getMana() < mana) {
            return false;
        }
        apply(heroService, hero);
        hero.reduceMana(mana);
        return true;
    }

    abstract protected void apply(IHeroesService heroService, Hero hero);

    int needMana() {
        return mana;
    }

    public abstract String info();

    public boolean levelUp(Hero hero) {
        if (hero.getExp() < needExp && !canLevelUp()) {
            return false;
        }
        upgrade();
        hero.reduceExp(needExp);
        return true;
    }

    ;

    abstract protected void upgrade();

    int needExpToLevelUp() {
        return needExp;
    }

    public abstract boolean canLevelUp();

}
