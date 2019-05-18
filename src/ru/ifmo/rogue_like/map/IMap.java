package ru.ifmo.rogue_like.map;

import java.util.List;

import ru.ifmo.rogue_like.rendering_system.IRenderable;

public interface IMap extends IRenderable {
    List<List<ISquare>> getField();

    void updateMap(int x, int y, char direction);
}
