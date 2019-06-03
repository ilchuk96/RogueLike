package ru.ifmo.rogue_like;

import ru.ifmo.rogue_like.map.IMap;
import ru.ifmo.rogue_like.map.Map;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SaveMenu extends JFrame {

    private JButton save;
    private JButton cancel;
    private JTextField filename;
    private JPanel buttons;

    public SaveMenu(IMap map) {
        super("Save map");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        save = new JButton("Save");
        cancel = new JButton("Cancel");
        cancel.addActionListener(actionEvent -> this.dispose());
        save.addActionListener(actionEvent -> {
            try {
                map.saveMap(filename.getText());
                this.dispose();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this,
                        "Can't create file",
                        "Save error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        filename = new JTextField("Enter file name", 30);
        buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        setLayout(new FlowLayout());
        buttons.add(save, BorderLayout.EAST);
        buttons.add(cancel, BorderLayout.WEST);
        add(filename, BorderLayout.NORTH);
        add(buttons, BorderLayout.SOUTH);
        setSize(new Dimension(400, 100));
        setResizable(false);
        setVisible(true);

    }
}