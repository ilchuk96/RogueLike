package ru.ifmo.rogue_like.heroes.player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Queue;

import ru.ifmo.rogue_like.heroes.MoveDirection;

public class PlayerListener implements KeyListener {
    private Queue<MoveDirection> directions = new LinkedList<>();

    public MoveDirection popLastDirection() {
        return directions.poll();
    }

    public boolean hasTyped() {
        System.out.print(1);
        return !directions.isEmpty();
    }

    public MoveDirection peekLastDirection() {
        return directions.peek();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        // TODO: move to configuration file
        if (keyEvent.getKeyCode() == KeyEvent.VK_W) {
            directions.add(new MoveDirection(0, -1));
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_A) {
            directions.add(new MoveDirection(-1, 0));
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_D) {
            directions.add(new MoveDirection(1, 0));
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_S) {
            directions.add(new MoveDirection(0, 1));
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
