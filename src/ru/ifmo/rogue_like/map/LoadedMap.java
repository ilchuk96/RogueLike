package ru.ifmo.rogue_like.map;

import ru.ifmo.rogue_like.renderer.IView;

import java.util.List;

public class LoadedMap implements IMap {
    @Override
    public List<List<ISquare>> getField() {
        return null;
    }

    @Override
    public void updateMap(int x, int y) {

    }

    @Override
    public IView getView() {
        return null;
    }
}
