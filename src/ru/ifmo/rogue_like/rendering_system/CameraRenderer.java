package ru.ifmo.rogue_like.rendering_system;

import ru.ifmo.rogue_like.rendering_system.camera.ICamera;

import javax.swing.*;
import java.io.IOException;

public class CameraRenderer extends JFrame implements IRenderer {
    ICamera camera;

    private JLabel canvas;

    public CameraRenderer(ICamera camera) {
        super("RougeLike");
        canvas = new JLabel();
        this.camera = camera;
    }

    private void clear() throws IOException {
//        Runtime.getRuntime().exec("clear");
//        System.out.print("\033[H\033[2J");
//        System.out.flush();
        canvas.setText("");
    }


    @Override
    public void render() throws IOException {
        clear();
        StringBuilder text = new StringBuilder();
        char[][] view = camera.getView();
        for (char[] row : view) {
            for (char elem : row) {
                text.append(elem);
                //System.out.print(elem);
            }
            //System.out.println();
            text.append("\n");
        }
        canvas.setText(text.toString());
    }
}
