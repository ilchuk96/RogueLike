package ru.ifmo.rogue_like;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ru.ifmo.rogue_like.heroes.mobs.Hero;
import ru.ifmo.rogue_like.heroes.mobs.move_strategies.PlayerStrategy;
import ru.ifmo.rogue_like.heroes.player.PlayerListener;
import ru.ifmo.rogue_like.map.IMap;
import ru.ifmo.rogue_like.rendering_system.CameraRenderer;
import ru.ifmo.rogue_like.rendering_system.camera.Camera;
import ru.ifmo.rogue_like.rendering_system.camera.ICamera;

public class App {

    private IMap map;

    public App(IMap map) {
        this.map = map;
    }

    public void newGame() {

        //IMap map = new Map(1024, 1024);

        PlayerListener listener = new PlayerListener();

        Hero player = new Hero(new PlayerStrategy(listener), map.getHeroX(), map.getHeroY());
        map.addPlayer(player);
        ICamera camera = new Camera(41, 41, player);
        List<Hero> heroes = new ArrayList<>();
        heroes.add(player);

        camera.addRenderableObject(map);
        /*
        for (Hero hero : heroes) {
            camera.addRenderableObject(hero);
        }
        */
        CameraRenderer renderer = new CameraRenderer(camera, listener);
        try {
            player.move(map);
            camera.update(System.currentTimeMillis());
            renderer.render();
            while (renderer.isVisible()) {
                if (listener.hasTyped()) {
                    Hero newHero = map.updateMap(player.getX(), player.getY(), listener.peekLastDirection());
                    if (newHero != null) {
                        heroes.add(newHero);
                        //camera.addRenderableObject(newHero);
                    }
                    List<Hero> toRemove = new ArrayList<>();
                    for (Hero hero : heroes) {
                        hero.move(map);
                        if (hero == player) {
                            System.out.println("p");
                        }
                        if (hero.isDead()) {
                            toRemove.add(hero);
                        }
                    }
                    heroes.removeAll(toRemove);
                    for (Hero hero : toRemove) {
                        map.deleteMob(hero.getX(), hero.getY());
                    }
                    camera.update(System.currentTimeMillis());
                    renderer.render();
                }
                Thread.sleep(30);
            }
            System.out.println("finish");
        } catch (Exception e) {
            e.printStackTrace();
        }
        SaveMenu sm = new SaveMenu(map);
        renderer.dispose();
    }

    public void start() {
//        MenuEntry newGame = new MenuEntry("New Game", this::newGame);
//        Menu menu = new Menu(Collections.singletonList(newGame));
//        menu.showMenu();
        newGame();
    }
}

