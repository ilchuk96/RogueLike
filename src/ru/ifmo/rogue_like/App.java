package ru.ifmo.rogue_like;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.ifmo.rogue_like.heroes.mobs.Hero;
import ru.ifmo.rogue_like.heroes.mobs.move_strategies.PlayerStrategy;
import ru.ifmo.rogue_like.heroes.player.PlayerListener;
import ru.ifmo.rogue_like.map.IMap;
import ru.ifmo.rogue_like.map.RandomMap;
import ru.ifmo.rogue_like.menu.Menu;
import ru.ifmo.rogue_like.menu.MenuEntry;
import ru.ifmo.rogue_like.rendering_system.CameraRenderer;
import ru.ifmo.rogue_like.rendering_system.camera.Camera;
import ru.ifmo.rogue_like.rendering_system.camera.ICamera;

public class App {

    private IMap map;

    public App(IMap map) {
        this.map = map;
    }

    public void newGame() {

        //IMap map = new RandomMap(1024, 1024);

        PlayerListener listener = new PlayerListener();

        Hero player = new Hero(new PlayerStrategy(listener), 513, 512);
        ICamera camera = new Camera(41, 41, player);
        List<Hero> heroes = new ArrayList<>();
        heroes.add(player);

        camera.addRenderableObject(map);
        for (Hero hero : heroes) {
            camera.addRenderableObject(hero);
        }
        CameraRenderer renderer = new CameraRenderer(camera, listener);
        try {
            player.move(map);
            camera.update(System.currentTimeMillis());
            renderer.render();
            while (true) {
                if (listener.hasTyped()) {
                    Hero newHero = map.updateMap(player.getX(), player.getY(), listener.peekLastDirection());
                    if (newHero != null) {
                        heroes.add(newHero);
                        camera.addRenderableObject(newHero);
                    }
                    List<Hero> toRemove = new ArrayList<>();
                    for (Hero hero : heroes) {
                        hero.move(map);
                        if (hero.isDead()) {
                            toRemove.add(hero);
                        }
                    }
                    heroes.removeAll(toRemove);
                    for (Hero hero : toRemove) {
                        camera.removeRenderableObject(hero);
                    }
                    camera.update(System.currentTimeMillis());
                    renderer.render();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
//        MenuEntry newGame = new MenuEntry("New Game", this::newGame);
//        Menu menu = new Menu(Collections.singletonList(newGame));
//        menu.showMenu();
        newGame();
    }
}

