package ru.ifmo.rogue_like.heroes.magic;

import ru.ifmo.rogue_like.heroes.HeroesService;
import ru.ifmo.rogue_like.heroes.IHeroesService;
import ru.ifmo.rogue_like.heroes.mobs.Hero;
import ru.ifmo.rogue_like.heroes.mobs.HeroDecorator;
import ru.ifmo.rogue_like.heroes.mobs.IHero;
import ru.ifmo.rogue_like.heroes.mobs.move_strategies.Dilative;
import ru.ifmo.rogue_like.map.IMap;
import ru.ifmo.rogue_like.map.ISquare;

import java.util.List;

public class Fire extends Magic {

    private int damage;
    private int square;

    public Fire() {
        mana = 10;
        damage = 1;
        square = 3;

    }

    @Override
    protected void apply(IHeroesService heroService, Hero hero) {
        int x = hero.getX();
        int y = hero.getY();
        for (int i = -square; i <= square; i++) {
            for (int j = -square; j <= square; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                IHero target = heroService.getHero(x + i, y + j);
                if (target != null) {
                    target.getDamage(damage);
                }
            }
        }
    }

    @Override
    public String info() {
        return "Damages all characters in square " + square + "X" + square + " with " + damage + ", requires " + mana;
    }

    @Override
    protected void upgrade() {
        damage++;
        needExp *= 10;
    }

    @Override
    public boolean canLevelUp() {
        return damage == 5;
    }
}
