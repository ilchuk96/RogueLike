package ru.ifmo.rogue_like;

import ru.ifmo.rogue_like.heroes.Magic.Magic;
import ru.ifmo.rogue_like.heroes.Magic.MagicInventory;
import ru.ifmo.rogue_like.heroes.mobs.Hero;
import ru.ifmo.rogue_like.heroes.mobs.move_strategies.PlayerStrategy;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryMenu extends JFrame {

    private JLabel exp;
    private List<MagicPanel> magicPanel;
    private int inUse;
    private List<Magic> magics;
    private Hero hero;
    private MagicInventory magicInventory;

    private class MagicPanel extends JPanel {

        JLabel info;
        JButton levelUp;
        JButton equip;
        Magic magic;
        int number;

        public MagicPanel(Magic magic, int i) {
            info = new JLabel();
            number = i;
            info.setText(magic.info());
            levelUp = new JButton(magic.getLUInfo());
            equip = new JButton("equip");
            this.magic = magic;
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            levelUp.setEnabled(magic.canLevelUp() && hero.getExp() > magic.needExpToLevelUp());
            equip.setEnabled(number != inUse);
            equip.addActionListener(actionEvent -> {
                inUse = number;
                magicInventory.setInUse(inUse);
                InventoryMenu.this.update();
            });
            levelUp.addActionListener(actionEvent -> {
                this.magic.levelUp(hero);
                InventoryMenu.this.update();
            });
            info.setSize(10, 50);
            levelUp.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
            equip.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
            add(info, BorderLayout.WEST);
            add(Box.createHorizontalGlue());
            add(levelUp, BorderLayout.EAST);
            add(equip, BorderLayout.EAST);
        }

        public void update() {
            info.setText(magic.info());
            equip.setEnabled(number != inUse);
            levelUp.setText(magic.getLUInfo());
            levelUp.setEnabled(magic.canLevelUp() && hero.getExp() > magic.needExpToLevelUp());
        }

    }

    public InventoryMenu(Hero hero, MagicInventory inventory) {
        super("Inventory");
        this.hero = hero;
        magicInventory = inventory;
        magicPanel = new ArrayList<>();
        magics = inventory.getMagics();
        inUse = inventory.getInUse();
        exp = new JLabel();
        exp.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        exp.setText("Hero experience " + hero.getExp());
        exp.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        Box box = Box.createVerticalBox();
        box.add(exp);
        for (int i = 0; i < magics.size(); i++) {
            magicPanel.add(new MagicPanel(magics.get(i), i));
            box.add(Box.createVerticalStrut(10));
            box.add(magicPanel.get(i), BorderLayout.SOUTH);
        }
        //setSize(new Dimension(400, 100));
        setContentPane(box);
        pack();
        setSize(new Dimension(800, 200));
        setVisible(true);
    }

    public void update() {
        exp.setText("Hero experience " + hero.getExp());
        for (MagicPanel panel : magicPanel) {
            panel.update();
        }
    }
}
