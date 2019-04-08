package ru.ifmo.rogue_like.player;

import ru.ifmo.rogue_like.rendering_system.IView;
import ru.ifmo.rogue_like.rendering_system.camera.ICamera;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerListener implements KeyListener {

    private Player player;
    private ICamera camera;

    public PlayerListener(ICamera camera) {
        player = Player.getInstanse();
        this.camera = camera;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        boolean success = false;
        if (keyEvent.getKeyCode() == KeyEvent.VK_W) {
            success = player.move(1, 0, camera);
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_A) {
            success = player.move(0, -1, camera);
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_D) {
            success = player.move(0, 1, camera);
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_S) {
            success = player.move(-1, 0, camera);
        }
        if (success) {
            camera.setCameraPosition(player.getX(), player.getY());
            camera.update(System.currentTimeMillis());
        }


    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
