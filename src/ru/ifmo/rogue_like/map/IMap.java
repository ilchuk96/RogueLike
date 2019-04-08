package ru.ifmo.rogue_like.map;

import ru.ifmo.rogue_like.rendering_system.IView;

import java.util.List;

public interface IMap {
    List<List<ISquare>> getField();

    IView getView(long time);

    void updateMap(int x, int y, char direction);
}
