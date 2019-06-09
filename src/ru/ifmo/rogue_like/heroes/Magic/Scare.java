package ru.ifmo.rogue_like.heroes.Magic;

import ru.ifmo.rogue_like.heroes.mobs.Hero;
import ru.ifmo.rogue_like.heroes.mobs.HeroDecorator;
import ru.ifmo.rogue_like.heroes.mobs.move_strategies.Dilative;
import ru.ifmo.rogue_like.heroes.mobs.move_strategies.PlayerStrategy;
import ru.ifmo.rogue_like.map.IMap;
import ru.ifmo.rogue_like.map.ISquare;

import java.util.List;

public class Scare extends Magic {

    private int square;

    public Scare() {
        mana = 10;
        square = 1;
        needExp = 10;
    }

    @Override
    protected void apply(IMap map, Hero hero) {
        int x = hero.getX();
        int y = hero.getY();
        List<List<ISquare>> field = map.getField();
        for (int i = -square; i <= square; i++) {
            for (int j = -square; j <= square; j++) {
                if (field.get(x + i).get(y + j) instanceof HeroDecorator &&
                        !(((HeroDecorator) field.get(x + i).get(y + j)).getStrategy() instanceof PlayerStrategy)) {
                    ((HeroDecorator) field.get(x + i).get(y + j)).setStrategy(new Dilative());
                }
            }
        }
    }

    @Override
    public String info() {
        return "Scares all characters in square " + square + "X" + square + ", requires " + mana;
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
