package ru.ifmo.rogue_like.rendering_system;

import ru.ifmo.rogue_like.rendering_system.camera.ICamera;

import java.io.IOException;

public class CameraRenderer implements IRenderer {
    ICamera camera;

    public CameraRenderer(ICamera camera) {
        this.camera = camera;
    }

    private void clear() throws IOException {
        Runtime.getRuntime().exec("clear");
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    @Override
    public void render() throws IOException {
        clear();
        char[][] view = camera.getView();
        for (char[] row : view) {
            for (char elem : row) {
                System.out.print(elem);
            }
            System.out.println();
        }
    }
}
