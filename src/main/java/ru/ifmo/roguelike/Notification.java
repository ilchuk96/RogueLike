package ru.ifmo.roguelike;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Notification to player i.e. on death or save
 */
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

    public Notification(String notification, ActionListener onOk) {
        this(notification);
        ok.addActionListener(onOk);
    }
}