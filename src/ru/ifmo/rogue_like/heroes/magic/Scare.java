package ru.ifmo.rogue_like.heroes.magic;

import ru.ifmo.rogue_like.heroes.IHeroesService;
import ru.ifmo.rogue_like.heroes.mobs.Hero;
import ru.ifmo.rogue_like.heroes.mobs.IHero;
import ru.ifmo.rogue_like.heroes.mobs.move_strategies.Dilative;

public class Scare extends Magic {

    private int square;

    public Scare() {
        mana = 10;
        square = 1;

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
                    target.setStrategy(new Dilative());
                }
            }
        }
    }

    @Override
    public String info() {
        return "Scares all characters in square " + square + "X" + square + ", requires " + mana;
    }

    @Override
    protected void upgrade() {
        square++;
        needExp *= 10;
    }

    @Override
    public boolean canLevelUp() {
        return square == 5;
    }
}
