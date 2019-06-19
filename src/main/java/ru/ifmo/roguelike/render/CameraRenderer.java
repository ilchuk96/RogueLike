package ru.ifmo.roguelike.render;

import java.awt.*;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.stream.Collectors;
import javax.swing.*;

import ru.ifmo.roguelike.render.camera.ICamera;
import ru.ifmo.roguelike.Settings;

public class CameraRenderer extends JFrame implements IRenderer {
    private ICamera camera;
    private Canvas canvas;

    final private static int X_SIZE = Settings.getProperty("window.width", Integer.class);
    final private static int Y_SIZE = Settings.getProperty("window.height", Integer.class);
    final private static int FONT_SIZE = Settings.getProperty("window.fontSize", Integer.class);

    private int marginLeft = 10;
    private int marginTop = 10;

    public CameraRenderer(ICamera camera, KeyListener keyListener) {
        super("RougeLike");
        canvas = new Canvas(FONT_SIZE);
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(X_SIZE, Y_SIZE));
        this.setResizable(false);
        this.addKeyListener(keyListener);
        this.add(canvas);
        this.setVisible(true);
        this.camera = camera;
        render();
    }


    @Override
    public void render() {
        camera.update(System.currentTimeMillis());
        char[][] view = camera.getView();
        String caption = Arrays.stream(view)
                .map(String::new)
                .collect(Collectors.joining("<br>", "<html>", "</html>"));
        canvas.setText(caption);
    }
}
