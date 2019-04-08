package ru.ifmo.rogue_like;

import ru.ifmo.rogue_like.map.RandomMap;
import ru.ifmo.rogue_like.menu.Menu;
import ru.ifmo.rogue_like.menu.MenuEntry;
import ru.ifmo.rogue_like.player.Player;
import ru.ifmo.rogue_like.player.PlayerListener;
import ru.ifmo.rogue_like.rendering_system.CameraRenderer;
import ru.ifmo.rogue_like.rendering_system.camera.Camera;
import ru.ifmo.rogue_like.rendering_system.camera.ICamera;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class App {
    public void newGame() {
        ICamera camera = new Camera(41, 41, 0, 0);
        camera.addRenderableObject(new RandomMap());
        camera.addRenderableObject(Player.getInstanse());
        PlayerListener listener = new PlayerListener(camera);
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

