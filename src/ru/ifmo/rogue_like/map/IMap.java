package ru.ifmo.rogue_like.map;

import ru.ifmo.rogue_like.rendering_system.IRenderable;

import java.util.List;

public interface IMap extends IRenderable {
    List<List<ISquare>> getField();

    void updateMap(int x, int y, char direction);
}
