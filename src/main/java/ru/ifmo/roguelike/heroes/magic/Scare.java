package ru.ifmo.roguelike.heroes.magic;

import ru.ifmo.roguelike.heroes.IHeroesService;
import ru.ifmo.roguelike.heroes.mobs.IHero;
import ru.ifmo.roguelike.heroes.mobs.move.strategies.Dilative;

/**
 * Scares mobs in range
 */
public class Scare extends Magic {

    private int square;

    public Scare() {
        setMana(10);
        square = 1;
        setNeedExp(10);
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
                    target.setStrategy(new Dilative());
                }
            }
        }
    }

    @Override
    public String info() {
        return "Scares all characters in square " + square + "X" + square + ", requires " + getMana();
    }

    @Override
    public String getLUInfo() {
        if (canLevelUp()) {
            return "+1 to area for " + getNeedExp() + " exp";
        }
        return "Max Level";
    }

    @Override
    protected void upgrade() {
        square++;
        setNeedExp(getNeedExp() * 10);
    }

    @Override
    public boolean canLevelUp() {
        return square != 5;
    }
}
