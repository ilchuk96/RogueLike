package ru.ifmo.rogue_like.menu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.List;

public class Menu implements KeyListener {
    private int currentIndex = 0;
    private List<MenuEntry> entries;

    public Menu(List<MenuEntry> menuEntries) {
        currentIndex = 0;
        entries = menuEntries;
    }

    public void showMenu() throws IOException {
        render();
    }

    private void clear() throws IOException {
        Runtime.getRuntime().exec("clear");
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void render() throws IOException {
        clear();
        for (int index = 0; index < entries.size(); index++) {
            if(index == currentIndex) {
                System.out.print("> ");
            } else {
                System.out.print("  ");
            }
            System.out.println(entries.get(index).getName());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 38 || e.getKeyCode() == 87) {// up
            currentIndex++;
            if (currentIndex > this.entries.size()) {
                currentIndex = currentIndex % this.entries.size();
            }
            try {
                render();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if (e.getKeyCode() == 40 || e.getKeyCode() == 83) {// down
            currentIndex--;
            while (currentIndex < 0) {
                currentIndex += this.entries.size();
            }
            try {
                render();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if (e.getKeyCode() == 13) { // enter
            entries.get(currentIndex).choose();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
