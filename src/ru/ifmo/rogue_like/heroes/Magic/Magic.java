package ru.ifmo.rogue_like.heroes.Magic;

import ru.ifmo.rogue_like.heroes.mobs.Hero;
import ru.ifmo.rogue_like.heroes.mobs.HeroDecorator;
import ru.ifmo.rogue_like.map.IMap;

public abstract class Magic {

    protected int mana;

    protected int needExp;

    public boolean cast(IMap map, Hero hero) {
        if (hero.getMana() < mana) {
            return false;
        }
        apply(map, hero);
        hero.reduceMana(mana);
        return true;
    }

    abstract protected void apply(IMap map, Hero hero);

    int needMana() {
        return mana;
    };

    public abstract String info();

    public boolean levelUp(Hero hero) {
        if (hero.getExp() < needExp && !canLevelUp()) {
            return false;
        }
        upgrade();
        hero.reduceExp(needExp);
        return true;
    };

    abstract protected void upgrade();

    int needExpToLevelUp() {
        return needExp;
    }

    public abstract boolean canLevelUp();

}
