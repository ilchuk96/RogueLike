package ru.ifmo.rogue_like;

import ru.ifmo.rogue_like.menu.Menu;
import ru.ifmo.rogue_like.menu.MenuEntry;
import ru.ifmo.rogue_like.rendering_system.CameraRenderer;
import ru.ifmo.rogue_like.rendering_system.camera.Camera;
import ru.ifmo.rogue_like.rendering_system.camera.ICamera;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class App {
    public void newGame() {
        ICamera camera = new Camera(40, 40, 0, 0);
        CameraRenderer renderer = new CameraRenderer(camera);
        try {
            while (true) {
                Thread.sleep(16);
                camera.update(System.currentTimeMillis());
                renderer.render();
            }
        } catch(Exception e) {
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
