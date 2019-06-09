package ru.ifmo.rogue_like;

import java.awt.*;
import javax.swing.*;

public class Notification extends JFrame {

    private JButton ok;
    private JPanel buttons;
    private JTextField textField;

    public Notification(String notification) {
        super("Notification");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ok = new JButton("Ok");
        ok.addActionListener(actionEvent -> this.dispose());
        buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        textField = new JTextField(notification);
        textField.setEditable(false);
        setLayout(new FlowLayout());
        buttons.add(ok, BorderLayout.WEST);
        add(textField, BorderLayout.NORTH);
        add(buttons, BorderLayout.SOUTH);
        setSize(new Dimension(400, 100));
        setResizable(false);
        setVisible(true);

    }
}