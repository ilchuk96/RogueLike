package ru.ifmo.rogue_like.rendering_system;

import java.awt.*;
import java.awt.event.KeyListener;
import javax.swing.*;

import ru.ifmo.rogue_like.Settings;
import ru.ifmo.rogue_like.rendering_system.camera.ICamera;

public class CameraRenderer extends JFrame implements IRenderer {
    private ICamera camera;
    private JLabel canvas;

    final private static int X_SIZE = Settings.getProperty("window.width", Integer.class);
    final private static int Y_SIZE = Settings.getProperty("window.height", Integer.class);
    final private static int FONT_SIZE = Settings.getProperty("window.fontSize", Integer.class);

    private int marginLeft = 10;
    private int marginTop = 10;

    public CameraRenderer(ICamera camera, KeyListener keyListener) {
        super("RougeLike");
        canvas = new JLabel();
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(400, 300)); // What it does?

        this.setSize(new Dimension(X_SIZE, Y_SIZE));
        this.setResizable(false);
        this.addKeyListener(keyListener);
        this.add(canvas);
        this.setVisible(true);
        this.camera = camera;
    }

    private void clear() {
        canvas.getGraphics().clearRect(0, 0, X_SIZE, Y_SIZE);
    }


    @Override
    public void render() {
        camera.update(System.currentTimeMillis());
        clear();
        char[][] view = camera.getView();
        int rowNumber = 0;
        Graphics graphics = canvas.getGraphics();
        graphics.setFont(new Font(Font.MONOSPACED, Font.BOLD, FONT_SIZE));
        graphics.setColor(Color.white);
        for (char[] row : view) {
            StringBuilder text = new StringBuilder();
            for (char elem : row) {
                text.append(elem);
            }
            graphics.drawString(text.toString(), marginLeft, rowNumber * 10 + marginTop);
            rowNumber++;
        }
    }
}
