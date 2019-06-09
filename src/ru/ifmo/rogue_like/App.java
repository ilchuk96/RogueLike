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
