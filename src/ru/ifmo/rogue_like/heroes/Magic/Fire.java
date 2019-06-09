package ru.ifmo.rogue_like.heroes.Magic;

import ru.ifmo.rogue_like.heroes.mobs.Hero;
import ru.ifmo.rogue_like.heroes.mobs.HeroDecorator;
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
    protected void apply(IMap map, Hero hero) {
        int x = hero.getX();
        int y = hero.getY();
        List<List<ISquare>> field = map.getField();
        for (int i = -square; i <= square; i++) {
            for (int j = -square; j <= square; j++) {
                if (field.get(x + i).get(y + j) instanceof HeroDecorator && (i != 0 || j != 0)) {
                    ((HeroDecorator) field.get(x + i).get(y + j)).getDamage(damage);
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
