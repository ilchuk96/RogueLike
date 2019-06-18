package ru.ifmo.roguelike.render;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {

    private JLabel front;
    private JLabel back;

    public Canvas(int fontSize) {
        super();
        setBackground(Color.black);
        front = new JLabel();
        front.setFont(new Font(Font.MONOSPACED, Font.BOLD, fontSize));
        front.setBackground(Color.black);
        front.setForeground(Color.white);
        front.setOpaque(true);
        back = new JLabel();
        back.setFont(new Font(Font.MONOSPACED, Font.BOLD, fontSize));
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.setOpaque(true);
        back.setVisible(false);
        front.setVisible(true);
        setLayout(new OverlayLayout(this));
        add(front);
        add(back);
    }

    public void setText(String text) {
        back.setText(text);
        JLabel tmp = front;
        front = back;
        back = tmp;
        front.setVisible(true);
        back.setVisible(false);
    }

    public String getText() {
        return front.getText();
    }
}
