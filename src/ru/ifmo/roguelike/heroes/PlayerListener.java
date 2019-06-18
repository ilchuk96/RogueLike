package ru.ifmo.roguelike.heroes;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ru.ifmo.roguelike.App;
import ru.ifmo.roguelike.InventoryMenu;
import ru.ifmo.roguelike.Notification;
import ru.ifmo.roguelike.Settings;
import ru.ifmo.roguelike.commands.HeroMoveCommand;
import ru.ifmo.roguelike.commands.ICommand;
import ru.ifmo.roguelike.commands.generators.ICommandGenerator;
import ru.ifmo.roguelike.heroes.mobs.IHero;
import ru.ifmo.roguelike.heroes.mobs.move.strategies.PlayerStrategy;
import ru.ifmo.roguelike.map.IMap;
import ru.ifmo.roguelike.save.SaveException;
import ru.ifmo.roguelike.save.SaveService;

public class PlayerListener implements KeyListener, ICommandGenerator {
    private final int upKeyCode;
    private final int downKeyCode;
    private final int leftKeyCode;
    private final int rightKeyCode;
    private final int skipKeyCode;
    private final int castKeyCode;
    private final int inventoryKeyCode;

    private IHero hero;
    private IMap map;
    private IHeroesService heroesService;
    private MoveAction lastAction = null;
    private App app;

    public PlayerListener(IHero hero, IMap map,
                          IHeroesService heroesService,
                          App app) {
        this.hero = hero;
        this.map = map;
        this.heroesService = heroesService;
        this.app = app;
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
    }

    @Override
    public synchronized void keyPressed(KeyEvent keyEvent) {
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
            lastAction = action;
            notify();
        }
        if (keyEvent.getKeyCode() == inventoryKeyCode) {
            InventoryMenu im = new InventoryMenu(hero, (PlayerStrategy) hero.getStrategy());
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_F5) {
            SaveService saveService = new SaveService();
            try {
                saveService.save(app);
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

    @Override
    public ICommand getCommand() {
        MoveAction returnAction = lastAction;
        lastAction = null;
        if (returnAction != null) {
            return new HeroMoveCommand(hero, map, heroesService, returnAction);
        }
        return null;
    }

    @Override
    public synchronized boolean isReady() {
        return lastAction != null;
    }
}
