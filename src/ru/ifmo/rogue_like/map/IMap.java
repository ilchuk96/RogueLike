package ru.ifmo.rogue_like.map;

import java.util.List;

import ru.ifmo.rogue_like.heroes.MoveAction;
import ru.ifmo.rogue_like.heroes.mobs.HeroDecorator;
import ru.ifmo.rogue_like.heroes.mobs.IHero;
import ru.ifmo.rogue_like.rendering_system.IRenderable;

public interface IMap extends IRenderable {
    List<List<ISquare>> getField();

    IHero updateMap(int x, int y, char direction);

    default IHero updateMap(int x, int y, MoveAction direction) {
        int xDir = direction.getX();
        int yDir = direction.getY();
        char charDirection = 'w';
        if (xDir == 1) {
            charDirection = 'd';
        }
        if (xDir == -1) {
            charDirection = 'a';
        }
        if (yDir == 1) {
            charDirection = 's';
        }
        return updateMap(x, y, charDirection);
    }

    void move(int x, int y, int dx, int dy);

    boolean isEmpty(int x, int y);

    int getPlayerSpawnX();
    int getPlayerSpawnY();
}
