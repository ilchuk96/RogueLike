package ru.ifmo.rogue_like;

import java.util.List;

import ru.ifmo.rogue_like.heroes.mobs.HeroDecorator;
import ru.ifmo.rogue_like.map.IMap;
import ru.ifmo.rogue_like.rendering_system.CameraRenderer;

public class App {

    private IMap map;
    private CameraRenderer renderer;

    public App(IMap map, CameraRenderer renderer) {
        this.map = map;
        this.renderer = renderer;
        renderer.render();
    }

    public void update() {
        List<HeroDecorator> heroes = map.getHeroes();
        for (HeroDecorator hero : heroes) {
            hero.move(map);
        }
        renderer.render();
    }

    public IMap getMap() {
        return map;
    }
}
//    public void newGame() {
//
//        //IMap map = new Map(1024, 1024);
//
//        PlayerListener listener = new PlayerListener(map);
//
//        HeroDecorator player = new ConfusedHeroDecorator(new Hero(new PlayerStrategy(), map.getHeroX(), map.getHeroY()));
//        map.addPlayer(player);
//        ICamera camera = new Camera(41, 41, player);
//        List<HeroDecorator> heroes;
//
//        camera.addRenderableObject(map);
//        /*
//        for (Hero hero : heroes) {
//            camera.addRenderableObject(hero);
//        }
//        */
//        CameraRenderer renderer = new CameraRenderer(camera, listener);
//        try {
//            //player.move(map);
//            renderer.render();
//            while (renderer.isVisible() && ! player.isDead()) {
//                if (listener.hasTyped()) {
//                    heroes = map.getHeroes();
//                    for (HeroDecorator hero : heroes) {
//                        hero.move(map);
//                    }
//                    renderer.render();
//                }
//                Thread.sleep(30);
//            }
//            System.out.println("finish");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        SaveMenu sm = new SaveMenu(map);
//        renderer.dispose();
//    }
//
//    public void start() {
////        MenuEntry newGame = new MenuEntry("New Game", this::newGame);
////        Menu menu = new Menu(Collections.singletonList(newGame));
////        menu.showMenu();
//        newGame();
//    }

