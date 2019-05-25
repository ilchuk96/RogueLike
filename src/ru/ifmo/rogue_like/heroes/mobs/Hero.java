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

public class Hero implements IRenderable, IPositionable {

    private final IHeroStrategy strategy;
    private int x;
    private int y;

    public Hero(IHeroStrategy heroStrategy, int x, int y) {
        this.strategy = heroStrategy;
        this.x = x;
        this.y = y;
    }

    public boolean move(IMap map) {
        MoveDirection moveDirection = strategy.moveDirection(map, x, y);
        if (moveDirection == null) {
            return false;
        }
        List<List<ISquare>> field = map.getField();
        if (field.get(x + moveDirection.getX()).get(y + moveDirection.getY()) instanceof Wall) {
            return false;
        }
        x += moveDirection.getX();
        y += moveDirection.getY();
        return true;
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
        if (!(strategy instanceof PlayerStrategy)) {
            Random random = new Random();
            int r = random.nextInt(20);
            return r == 0;
        }
        return false;
    }
}
