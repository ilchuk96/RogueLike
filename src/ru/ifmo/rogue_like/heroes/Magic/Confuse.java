package ru.ifmo.rogue_like.heroes.Magic;

import ru.ifmo.rogue_like.heroes.mobs.Hero;
import ru.ifmo.rogue_like.heroes.mobs.HeroDecorator;
import ru.ifmo.rogue_like.map.IMap;
import ru.ifmo.rogue_like.map.ISquare;

import java.util.List;

public class Confuse extends Magic {

    private int time;
    private int square;

    public Confuse() {
        mana = 10;
        time = 5;
        square = 1;

    }

    @Override
    protected void apply(IMap map, Hero hero) {
        int x = hero.getX();
        int y = hero.getY();
        List<List<ISquare>> field = map.getField();
        for (int i = -square; i <= square; i++) {
            for (int j = -square; j <= square; j++) {
                if (field.get(x + i).get(y + j) instanceof HeroDecorator && (i != 0 || j != 0)) {
                    ((HeroDecorator) field.get(x + i).get(y + j)).confuse(time);
                }
            }
        }
    }

    @Override
    public String info() {
        return "Confuses all characters in square " + square + "X" + square + " on " + time + " woves, requires " + mana;
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