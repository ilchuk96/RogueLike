package ru.ifmo.rogue_like.player;

import ru.ifmo.rogue_like.map.IMap;
import ru.ifmo.rogue_like.rendering_system.IRenderable;
import ru.ifmo.rogue_like.rendering_system.IView;
import ru.ifmo.rogue_like.rendering_system.camera.ICamera;

public class Player implements IRenderable {
    private static Player INSTANSE;

    private static char WALL = '#';
    private int x;
    private int y;

    private Player(int xx, int yy) {
        x = xx;
        y = yy;
    }

    ;

    public static Player getInstanse(int x, int y) {
        if (INSTANSE == null) {
            INSTANSE = new Player(x, y);
        }
        return INSTANSE;
    }

    public boolean move(int vx, int vy, IMap map) {
        char[][] charMap = map.getView(0).getView();
        //TODO: check array index bounds
        if (charMap[x + vx][y + vy] == WALL) {
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
