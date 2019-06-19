package ru.ifmo.roguelike;

import java.awt.*;
import java.io.File;
import javax.swing.*;

import ru.ifmo.roguelike.save.LoadException;
import ru.ifmo.roguelike.save.SaveService;
import ru.ifmo.roguelike.map.IMap;

public class LoadMenu extends JFrame {

    private JButton load;
    private JButton random;
    private JTextField filename;
    private JPanel buttons;
    private IMap map;

    public LoadMenu() {
        super("Select map");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        load = new JButton("Load");
        random = new JButton("Random");
        random.addActionListener(actionEvent -> {
            App app = new App();
            this.dispose();
        });
        load.setEnabled((new File(Settings.getProperty("save.file", String.class))).exists());
        load.addActionListener(actionEvent -> {
            try {
                SaveService loadService = new SaveService();
                App app = loadService.load();
                this.dispose();
            } catch (LoadException e) {
                JOptionPane.showMessageDialog(this,
                        "File not found",
                        "Read error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        setLayout(new FlowLayout());
        buttons.add(load, BorderLayout.EAST);
        buttons.add(random, BorderLayout.WEST);
        add(buttons, BorderLayout.SOUTH);
        setSize(new Dimension(400, 100));
        setResizable(false);
        setVisible(true);

    }

    public IMap getMap() {
        return map;
    }
}
