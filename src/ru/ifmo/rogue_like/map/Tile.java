package ru.ifmo.rogue_like.map;

import java.util.ArrayList;
import java.util.List;

public class Tile {
    List<List<ISquare>> tile;

    Tile() {
        tile = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            List<ISquare> lst = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                lst.add(null);
            }
            tile.add(lst);
        }
    }

    public boolean setSquare(int x, int y, ISquare square) {
        if (0 <= x && x < 4 && 0 <= y && y < 4){
            (tile.get(x)).set(y, square);
            return true;
        }
        return false;
    }

    public ISquare getSquare(int x, int y) {
        return (tile.get(x)).get(y);
    }
}
