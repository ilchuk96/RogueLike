package ru.ifmo.roguelike.map;

import java.util.List;

import ru.ifmo.roguelike.heroes.mobs.IHero;
import ru.ifmo.roguelike.render.IRenderable;
import ru.ifmo.roguelike.heroes.MoveAction;

/**
 * Game map
 */
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
