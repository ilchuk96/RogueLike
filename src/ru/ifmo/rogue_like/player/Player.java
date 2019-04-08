package ru.ifmo.rogue_like.player;

import ru.ifmo.rogue_like.map.IMap;
import ru.ifmo.rogue_like.rendering_system.camera.ICamera;

public class Player {
    private static Player INSTANSE;

    private static char WALL = 'w';
    private int x;
    private int y;

    private Player() {
        x = 0;
        y = 0;
    };
    public static Player getInstanse() {
        if (INSTANSE == null) {
            INSTANSE = new Player();
        }
        return INSTANSE;
    }

    public boolean move(int vx, int vy, ICamera map) {
        char[][] charMap = map.getView();
        if (charMap[x+vx][y+vy] == WALL) {
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

}
