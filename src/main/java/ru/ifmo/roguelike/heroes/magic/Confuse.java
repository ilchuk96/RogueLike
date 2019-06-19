package ru.ifmo.roguelike.heroes.magic;

import ru.ifmo.roguelike.heroes.IHeroesService;
import ru.ifmo.roguelike.heroes.mobs.IHero;
import ru.ifmo.roguelike.heroes.mobs.HeroDecorator;

public class Confuse extends Magic {

    private int time;
    private int square;

    public Confuse() {
        mana = 10;
        time = 5;
        square = 1;
        needExp = 10;
    }

    @Override
    protected void apply(IHeroesService heroService, IHero hero) {
        int x = hero.getX();
        int y = hero.getY();
        for (int i = -square; i <= square; i++) {
            for (int j = -square; j <= square; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                IHero target = heroService.getHero(x + i, y + j);
                if (target != null) {
                    if (target instanceof HeroDecorator) {
                        ((HeroDecorator) target).confuse(time);
                    }
                }
            }
        }
    }

    @Override
    public String info() {
        return "Confuses all characters in square " + square + "X" + square + " on " + time + " woves, requires " + mana;
    }

    @Override
    public String getLUInfo() {
        if (canLevelUp()) {
            return "+1 to area for " + needExp + " exp";
        }
        return "Max Level";
    }

    @Override
    protected void upgrade() {
        square++;
        needExp *= 10;
    }

    @Override
    public boolean canLevelUp() {
        return square != 5;
    }
}