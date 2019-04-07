package ru.ifmo.rogue_like.map;

import ru.ifmo.rogue_like.map.squares.Floor;
import ru.ifmo.rogue_like.map.squares.Wall;
import ru.ifmo.rogue_like.renderer.IView;

import java.util.ArrayList;
import java.util.List;

public class RandomMap implements IMap {
    private List<List<ISquare>> field;

    RandomMap() {
        int maxX = 1024;
        int maxY = 1024;

        field = new ArrayList<>();
        for (int i = 0; i < maxX; i++) {
            List<ISquare> lst = new ArrayList<>();
            for (int j = 0; j < maxY; j++) {
                lst.add(null);
            }
            field.add(lst);
        }

        int curX = maxX / 2;
        int curY = maxY / 2;

        field.get(curX).set(curY, new Floor());

        for (int i = -2; i <= 0; i++) {
            for (int j = -3; j <= 4; j++) {
                (field.get(curX + i)).set(curY + j, new Floor());
            }
        }
        for (int j = -3; j <= 4; j++) {
            (field.get(curX - 3)).set(curY + j, new Wall());
        }
    }

    private Tile getNewTile() {
        return null;
    }

    @Override
    public List<List<ISquare>> getField() {
        return field;
    }

    @Override
    public void updateMap(int x, int y) {
        throw new UnsupportedOperationException();
    }

    @Override
    public IView getView() {
        throw new UnsupportedOperationException();
    }
}
