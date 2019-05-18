package ru.ifmo.rogue_like.map;

import java.util.List;

import ru.ifmo.rogue_like.heroes.MoveDirection;
import ru.ifmo.rogue_like.rendering_system.IRenderable;

public interface IMap extends IRenderable {
    List<List<ISquare>> getField();

    void updateMap(int x, int y, char direction);

    default void updateMap(int x, int y, MoveDirection direction) {
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
        updateMap(x, y, charDirection);
    }
}
