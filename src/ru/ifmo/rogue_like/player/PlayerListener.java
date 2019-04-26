package ru.ifmo.rogue_like.player;

import ru.ifmo.rogue_like.map.IMap;
import ru.ifmo.rogue_like.rendering_system.IView;
import ru.ifmo.rogue_like.rendering_system.camera.ICamera;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerListener implements KeyListener {

    private Player player;
    private IMap map;
    private ICamera camera;

    public PlayerListener(IMap map, ICamera camera, int x, int y) {
        player = Player.getInstanse(x, y);
        this.map = map;
        this.camera = camera;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    public void keyPressed(char c) {
        boolean success = false;
        if (c == 'w') {
            success = player.move(1, 0, map);
        }
        if (c == 'a') {
            success = player.move(0, -1, map);
        }
        if (c == 'd') {
            success = player.move(0, 1, map);
        }
        if (c == 's') {
            success = player.move(-1, 0, map);
        }
        if (success) {
            camera.setCameraPosition(player.getX(), player.getY());
            camera.update(System.currentTimeMillis());
        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        boolean success = false;
        if (keyEvent.getKeyCode() == KeyEvent.VK_W) {
            map.updateMap(player.getX(), player.getY(), 'w');
            success = player.move(0, -1, map);
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_A) {
            map.updateMap(player.getX(), player.getY(), 'a');
            success = player.move(-1, 0, map);
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_D) {
            map.updateMap(player.getX(), player.getY(), 'd');
            success = player.move(1, 0, map);
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_S) {
            map.updateMap(player.getX(), player.getY(), 's');
            success = player.move(0, 1, map);
        }
        if (success) {
            camera.setCameraPosition(player.getX(), player.getY());
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
