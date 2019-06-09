package ru.ifmo.rogue_like.heroes.player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ru.ifmo.rogue_like.App;
import ru.ifmo.rogue_like.InventoryMenu;
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

    private Hero player;

    public PlayerListener(IMap map) {
        playerStrategy = new PlayerStrategy();
        player = new Hero(playerStrategy, map.getHeroX(), map.getHeroY());
        HeroDecorator heroDecorator = new ConfusedHeroDecorator(player);
        map.addPlayer(heroDecorator);
        ICamera camera = new Camera(41, 41, heroDecorator);
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
            InventoryMenu lm = new InventoryMenu(player, playerStrategy.magics);
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
