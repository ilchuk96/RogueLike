package ru.ifmo.rogue_like;

import java.awt.*;
import javax.swing.*;

import ru.ifmo.rogue_like.map.IMap;
import ru.ifmo.rogue_like.map.Map;
import ru.ifmo.rogue_like.save_service.LoadException;
import ru.ifmo.rogue_like.save_service.LoadService;

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
            map = new Map(1024, 1024);
            this.dispose();
        });
        load.addActionListener(actionEvent -> {

            try {
                LoadService loadService = new LoadService(filename.getText());
                map = loadService.load();
                this.dispose();
            } catch (LoadException e) {
                JOptionPane.showMessageDialog(this,
                        "File not found",
                        "Read error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        filename = new JTextField("Enter file name", 30);
        buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        setLayout(new FlowLayout());
        buttons.add(load, BorderLayout.EAST);
        buttons.add(random, BorderLayout.WEST);
        add(filename, BorderLayout.NORTH);
        add(buttons, BorderLayout.SOUTH);
        setSize(new Dimension(400, 100));
        setResizable(false);
        setVisible(true);

    }

    public IMap getMap() {
        return map;
    }
}
