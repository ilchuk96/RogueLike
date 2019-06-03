package ru.ifmo.rogue_like.heroes.mobs;

import java.util.List;
import java.util.Random;

import ru.ifmo.rogue_like.heroes.MoveDirection;
import ru.ifmo.rogue_like.heroes.mobs.move_strategies.IHeroStrategy;
import ru.ifmo.rogue_like.heroes.mobs.move_strategies.PlayerStrategy;
import ru.ifmo.rogue_like.map.IMap;
import ru.ifmo.rogue_like.map.IPositionable;
import ru.ifmo.rogue_like.map.ISquare;
import ru.ifmo.rogue_like.map.squares.Wall;
import ru.ifmo.rogue_like.rendering_system.IRenderable;
import ru.ifmo.rogue_like.rendering_system.IView;

public class Hero implements IRenderable, IPositionable, ISquare {

    private final IHeroStrategy strategy;
    private int x;
    private int y;
    private int hp;

    public Hero(IHeroStrategy heroStrategy, int x, int y) {
        this.strategy = heroStrategy;
        this.x = x;
        this.y = y;
        hp = 5;
    }

    public boolean move(IMap map) {
        MoveDirection moveDirection = strategy.moveDirection(map, x, y);
        if (moveDirection == null) {
            return false;
        }
        List<List<ISquare>> field = map.getField();
        if (x + moveDirection.getX() < 0 || x + moveDirection.getX() >= field.size()) return false;
        if (y + moveDirection.getY() < 0 || y + moveDirection.getY() >= field.get(x + moveDirection.getX()).size()) return false;
        if (field.get(x + moveDirection.getX()).get(y + moveDirection.getY()) instanceof Wall) {
            return false;
        }
        if (field.get(x + moveDirection.getX()).get(y + moveDirection.getY()) instanceof Hero) {
            ((Hero) field.get(x + moveDirection.getX()).get(y + moveDirection.getY())).getDamage(2);
            System.out.println("hit");
            return false;
        }
        map.move(x, y, moveDirection.getX(), moveDirection.getY());
        x += moveDirection.getX();
        y += moveDirection.getY();
        System.out.println(x);
        return true;
    }

    public void getDamage(int damage) {
        hp -= damage;
        System.out.println(hp);
    }

    @Override
    public IView getView(long time) {
        return strategy.getHeroView(x, y);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    /*
    EXAMPLE
     */
    public boolean isDead() {
        return hp <= 0;
    }
}
