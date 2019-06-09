package ru.ifmo.rogue_like.heroes.player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ru.ifmo.rogue_like.App;
import ru.ifmo.rogue_like.Notification;
import ru.ifmo.rogue_like.Settings;
import ru.ifmo.rogue_like.heroes.MoveAction;
import ru.ifmo.rogue_like.heroes.mobs.ConfusedHeroDecorator;
import ru.ifmo.rogue_like.heroes.mobs.Hero;
import ru.ifmo.rogue_like.heroes.mobs.HeroDecorator;
import ru.ifmo.rogue_like.heroes.mobs.move_strategies.PlayerStrategy;
import ru.ifmo.rogue_like.map.IMap;
import ru.ifmo.rogue_like.rendering_system.CameraRenderer;
import ru.ifmo.rogue_like.rendering_system.camera.Camera;
import ru.ifmo.rogue_like.rendering_system.camera.ICamera;
import ru.ifmo.rogue_like.save_service.SaveException;
import ru.ifmo.rogue_like.save_service.SaveService;

public class PlayerListener implements KeyListener {

    private App app;

    private PlayerStrategy playerStrategy;

    private final int upKeyCode;
    private final int downKeyCode;
    private final int leftKeyCode;
    private final int rightKeyCode;
    private final int skipKeyCode;
    private final int castKeyCode;
    private final int inventoryKeyCode;

    public PlayerListener(IMap map) {
        playerStrategy = new PlayerStrategy();
        HeroDecorator player = new ConfusedHeroDecorator(new Hero(playerStrategy, map.getHeroX(), map.getHeroY()));
        map.addPlayer(player);
        ICamera camera = new Camera(41, 41, player);
        camera.addRenderableObject(map);
        CameraRenderer renderer = new CameraRenderer(camera, this);

        upKeyCode = KeyEvent.getExtendedKeyCodeForChar(
                Settings.getProperty("player.up", Character.class));
        downKeyCode = KeyEvent.getExtendedKeyCodeForChar(
                Settings.getProperty("player.down", Character.class));
        leftKeyCode = KeyEvent.getExtendedKeyCodeForChar(
                Settings.getProperty("player.left", Character.class));
        rightKeyCode = KeyEvent.getExtendedKeyCodeForChar(
                Settings.getProperty("player.right", Character.class));
        skipKeyCode = KeyEvent.getExtendedKeyCodeForChar(
                Settings.getProperty("player.skip", Character.class));
        castKeyCode = KeyEvent.getExtendedKeyCodeForChar(
                Settings.getProperty("player.cast", Character.class));
        inventoryKeyCode = KeyEvent.getExtendedKeyCodeForChar(
                Settings.getProperty("player.inventory", Character.class));

        app = new App(map, renderer);
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        // TODO: move to configuration file
        MoveAction action = null;
        if (keyEvent.getKeyCode() == upKeyCode) {
            action = new MoveAction(0, -1, 0);
        }
        if (keyEvent.getKeyCode() == leftKeyCode) {
            action = new MoveAction(-1, 0, 0);
        }
        if (keyEvent.getKeyCode() == rightKeyCode) {
            action = new MoveAction(1, 0, 0);
        }
        if (keyEvent.getKeyCode() == downKeyCode) {
            action = new MoveAction(0, 1, 0);
        }
        if (keyEvent.getKeyCode() == skipKeyCode) {
            action = new MoveAction(0, 0, 0);
        }
        if (keyEvent.getKeyCode() == castKeyCode) {
            action = new MoveAction(0, 0, 1);
        }
        if (action != null) {
            playerStrategy.setAction(action);
            app.update();
        }
        if (keyEvent.getKeyCode() == inventoryKeyCode) {
            //TODO: inventory
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_F5) {
            SaveService saveService = new SaveService();
            try {
                saveService.save(app.getMap());
                Notification notification = new Notification("Successfully saved!");
            } catch (SaveException e) {
                Notification notification = new Notification("Save error");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }
}
