package ru.ifmo.roguelike;

import ru.ifmo.roguelike.heroes.magic.Magic;
import ru.ifmo.roguelike.heroes.magic.MagicInventory;
import ru.ifmo.roguelike.heroes.mobs.IHero;
import ru.ifmo.roguelike.heroes.mobs.move.strategies.PlayerStrategy;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Frame with magic inventory to set up and update it
 */
public class InventoryMenu extends JFrame {

    private JLabel exp;
    private List<MagicPanel> magicPanel;
    private int inUse;
    private List<Magic> magics;
    private IHero hero;
    private MagicInventory magicInventory;
    private PlayerStrategy playerStrategy;

    private class MagicPanel extends JPanel {

        private JLabel info;
        private JButton levelUp;
        private JButton equip;
        private Magic magic;
        private int number;

        public MagicPanel(Magic magic, int i) {
            info = new JLabel();
            number = i;
            info.setText(magic.info());
            levelUp = new JButton(magic.getLUInfo());
            equip = new JButton("equip");
            this.magic = magic;
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            levelUp.setEnabled(magic.canLevelUp() && hero.getExp() >= magic.needExpToLevelUp());
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
            levelUp.setEnabled(magic.canLevelUp() && hero.getExp() >= magic.needExpToLevelUp());
        }

    }

    public InventoryMenu(IHero hero, PlayerStrategy playerStrategy) {
        super("Inventory");
        this.hero = hero;
        this.playerStrategy = playerStrategy;
        magicInventory = playerStrategy.getMagics();
        magicPanel = new ArrayList<>();
        magics = magicInventory.getMagics();
        inUse = magicInventory.getInUse();
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
        playerStrategy.updateMagics(magicInventory);
    }
}
