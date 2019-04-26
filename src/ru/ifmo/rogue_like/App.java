package ru.ifmo.rogue_like;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ru.ifmo.rogue_like.map.IMap;
import ru.ifmo.rogue_like.map.RandomMap;
import ru.ifmo.rogue_like.player.Player;
import ru.ifmo.rogue_like.player.PlayerListener;
import ru.ifmo.rogue_like.rendering_system.CameraRenderer;
import ru.ifmo.rogue_like.rendering_system.camera.Camera;
import ru.ifmo.rogue_like.rendering_system.camera.ICamera;

public class App {
    public void newGame() {
        ICamera camera = new Camera(41, 41, 0, 0);
        IMap map = new RandomMap();

        camera.addRenderableObject(map);
        camera.addRenderableObject(Player.getInstanse());

        PlayerListener listener = new PlayerListener(map, camera);
        CameraRenderer renderer = new CameraRenderer(camera, listener);
        try {
            renderer.render();
            while (true) {
                Thread.sleep(50);
                camera.update(System.currentTimeMillis());
                renderer.render();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException {
//        MenuEntry newGame = new MenuEntry("New Game", this::newGame);
//        Menu menu = new Menu(Collections.singletonList(newGame));
//        menu.showMenu();
        newGame();
    }
}

