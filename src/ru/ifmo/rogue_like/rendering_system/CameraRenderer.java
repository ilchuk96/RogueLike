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
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(400, 300));
        this.setSize(new Dimension(700, 500));
        this.setResizable(false);
        this.addKeyListener(keyListener);
        this.add(canvas);
        this.setVisible(true);
        this.camera = camera;
    }

    private void clear() throws IOException {
        canvas.getGraphics().clearRect(0, 0, 500, 500);
    }


    @Override
    public void render() throws IOException {
        clear();
        char[][] view = camera.getView();
        int rowNumber = 0;
        Graphics graphics = canvas.getGraphics();
        graphics.setFont(new Font(Font.MONOSPACED, Font.BOLD, 13));
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
