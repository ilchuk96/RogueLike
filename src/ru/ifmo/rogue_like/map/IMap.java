package ru.ifmo.rogue_like.map;

import ru.ifmo.rogue_like.renderer.IRenderable;

import java.util.List;

public interface IMap extends IRenderable {
    List<List<ISquare>> getField();

    void updateMap(int x, int y);
}
