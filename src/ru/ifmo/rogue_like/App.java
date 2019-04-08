package ru.ifmo.rogue_like;

import ru.ifmo.rogue_like.map.IMap;
import ru.ifmo.rogue_like.map.RandomMap;
import ru.ifmo.rogue_like.player.Player;
import ru.ifmo.rogue_like.player.PlayerListener;
import ru.ifmo.rogue_like.rendering_system.CameraRenderer;
import ru.ifmo.rogue_like.rendering_system.camera.Camera;
import ru.ifmo.rogue_like.rendering_system.camera.ICamera;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    public void newGame() {
        ICamera camera = new Camera(41, 41, 0, 0);
        IMap map = new RandomMap();

        camera.addRenderableObject(map);
        camera.addRenderableObject(Player.getInstanse());

        PlayerListener listener = new PlayerListener(map, camera);
        CameraRenderer renderer = new CameraRenderer(camera);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            renderer.render();
            while (true) {
                Thread.sleep(16);
                String line = reader.readLine();
                if (line != null) {
                    for (char c : line.toCharArray()) {
                        listener.keyPressed(c);
                    }
                    camera.update(System.currentTimeMillis());
                    renderer.render();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException {
        newGame();
    }
}

