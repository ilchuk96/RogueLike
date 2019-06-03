package ru.ifmo.rogue_like.heroes.player;

import java.util.List;

import ru.ifmo.rogue_like.map.IMap;
import ru.ifmo.rogue_like.map.ISquare;
import ru.ifmo.rogue_like.map.squares.Wall;
import ru.ifmo.rogue_like.rendering_system.IRenderable;
import ru.ifmo.rogue_like.rendering_system.IView;

public class Player implements IRenderable {
    private int x;
    private int y;

    private Player(int xx, int yy) {
        x = xx;
        y = yy;
    }

    public boolean move(int vx, int vy, IMap map) {
        List<List<ISquare>> field = map.getField();
        if (field.get(x + vx).get(y + vy) instanceof Wall) {
            return false;
        }
        x += vx;
        y += vy;
        return true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public IView getView(long time) {
        return new IView() {
            @Override
            public int getX() {
                return x;
            }

            @Override
            public int getY() {
                return y;
            }

            @Override
            public char[][] getView() {
                char[][] result = new char[1][1];
                result[0][0] = '$';
                return result;
            }
        };
    }
}
