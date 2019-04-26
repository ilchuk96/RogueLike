package ru.ifmo.rogue_like.rendering_system;

import java.awt.*;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.swing.*;

import ru.ifmo.rogue_like.rendering_system.camera.ICamera;

public class CameraRenderer extends JFrame implements IRenderer {
    private ICamera camera;
    private JLabel canvas;

    private int marginLeft = 10;
    private int marginTop = 10;

    public CameraRenderer(ICamera camera, KeyListener keyListener) {
        super("RougeLike");
        canvas = new JLabel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(400, 300));
        this.addKeyListener(keyListener);
        this.add(canvas);
        this.setVisible(true);
        this.camera = camera;
    }

    private void clear() throws IOException {
//        Runtime.getRuntime().exec("clear");
//        System.out.print("\033[H\033[2J");
//        System.out.flush();
        canvas.getGraphics().clearRect(0, 0, 500, 500);
//        canvas.setText("");
    }


    @Override
    public void render() throws IOException {
        clear();
        char[][] view = camera.getView();
        int rowNumber = 0;
        for (char[] row : view) {
            StringBuilder text = new StringBuilder();
            for (char elem : row) {
                // System.out.print(elem);
                text.append(elem);
            }
            //System.out.println();
//            text.append("\n");
            canvas.getGraphics().drawString(text.toString(), marginLeft, rowNumber * 10 + marginTop);
            rowNumber++;
        }
    }
}
