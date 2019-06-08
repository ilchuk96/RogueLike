package ru.ifmo.rogue_like.heroes.player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ru.ifmo.rogue_like.App;
import ru.ifmo.rogue_like.SaveMenu;
import ru.ifmo.rogue_like.heroes.MoveAction;
import ru.ifmo.rogue_like.heroes.mobs.ConfusedHeroDecorator;
import ru.ifmo.rogue_like.heroes.mobs.Hero;
import ru.ifmo.rogue_like.heroes.mobs.HeroDecorator;
import ru.ifmo.rogue_like.heroes.mobs.move_strategies.IHeroStrategy;
import ru.ifmo.rogue_like.heroes.mobs.move_strategies.PlayerStrategy;
import ru.ifmo.rogue_like.map.IMap;
import ru.ifmo.rogue_like.rendering_system.CameraRenderer;
import ru.ifmo.rogue_like.rendering_system.camera.Camera;
import ru.ifmo.rogue_like.rendering_system.camera.ICamera;

public class PlayerListener implements KeyListener {

    private App app;

    private PlayerStrategy playerStrategy;

    public PlayerListener(IMap map) {
        playerStrategy = new PlayerStrategy();
        HeroDecorator player = new ConfusedHeroDecorator(new Hero(playerStrategy, map.getHeroX(), map.getHeroY()));
        map.addPlayer(player);
        ICamera camera = new Camera(41, 41, player);
        camera.addRenderableObject(map);
        CameraRenderer renderer = new CameraRenderer(camera, this);
        app = new App(map, renderer);
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        // TODO: move to configuration file
        MoveAction action = null;
        if (keyEvent.getKeyCode() == KeyEvent.VK_W) {
            action = new MoveAction(0, -1, 0);
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_A) {
            action = new MoveAction(-1, 0, 0);
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_D) {
            action = new MoveAction(1, 0, 0);
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_S) {
            action = new MoveAction(0, 1, 0);
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_Q) {
            action = new MoveAction(0, 0, 0);
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_E) {
            action = new MoveAction(0, 0, 1);
        }
        if (action != null) {
            playerStrategy.setAction(action);
            app.update();
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_I) {
            //TODO: inventory
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_F5) {
            SaveMenu sm = new SaveMenu(app.getMap());
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }
}
