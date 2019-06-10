package ru.ifmo.rogue_like.heroes.player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import ru.ifmo.rogue_like.App;
import ru.ifmo.rogue_like.InventoryMenu;
import ru.ifmo.rogue_like.Settings;
import ru.ifmo.rogue_like.command_generators.ICommandGenerator;
import ru.ifmo.rogue_like.commands.HeroMoveCommand;
import ru.ifmo.rogue_like.commands.ICommand;
import ru.ifmo.rogue_like.heroes.IHeroesService;
import ru.ifmo.rogue_like.heroes.MoveAction;
import ru.ifmo.rogue_like.heroes.mobs.IHero;
import ru.ifmo.rogue_like.heroes.mobs.move_strategies.PlayerStrategy;
import ru.ifmo.rogue_like.map.IMap;
import ru.ifmo.rogue_like.save_service.SaveService;

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

    public PlayerListener(App app, IHero hero, IMap map, IHeroesService heroesService) {
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
    public void keyPressed(KeyEvent keyEvent) {
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
            app.update();
        }
        if (keyEvent.getKeyCode() == inventoryKeyCode) {
            //TODO: inventory
            InventoryMenu im = new InventoryMenu(hero, ((PlayerStrategy)hero.getStrategy()).magics);
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_F5) {
            SaveService saveService = new SaveService();
//            try {
//                saveService.save(app.getMap());
//                Notification notification = new Notification("Successfully saved!");
//            } catch (SaveException e) {
//                Notification notification = new Notification("Save error");
//            }
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
        if (lastAction != null) {
            return new HeroMoveCommand(hero, map, heroesService, lastAction);
        }
        return null;
    }
}
