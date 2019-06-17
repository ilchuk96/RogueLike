package ru.ifmo.roguelike;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ru.ifmo.roguelike.commands.generators.ICommandGenerator;
import ru.ifmo.roguelike.commands.ICommand;
import ru.ifmo.roguelike.heroes.IHeroesService;
import ru.ifmo.roguelike.heroes.PlayerListener;
import ru.ifmo.roguelike.heroes.mobs.Hero;
import ru.ifmo.roguelike.heroes.mobs.IHero;
import ru.ifmo.roguelike.heroes.mobs.move.strategies.PlayerStrategy;
import ru.ifmo.roguelike.map.IMap;
import ru.ifmo.roguelike.map.Map;
import ru.ifmo.roguelike.render.CameraRenderer;
import ru.ifmo.roguelike.render.camera.Camera;
import ru.ifmo.roguelike.render.camera.ICamera;
import ru.ifmo.roguelike.heroes.HeroesService;

public class App {
    private IMap map;
    private IHeroesService heroesService;
    private CameraRenderer renderer;
    private ICamera camera;
    private List<ICommandGenerator> commandsGenerators;

    public App() {
        map = new Map(Settings.getProperty("map.width", Integer.class),
                Settings.getProperty("map.height", Integer.class));
        PlayerStrategy playerStrategy = new PlayerStrategy();
        IHero hero = new Hero(playerStrategy, map.getPlayerSpawnX(), map.getPlayerSpawnY());
        heroesService = new HeroesService(hero);
        camera = new Camera(Settings.getProperty("camera.width", Integer.class),
                Settings.getProperty("camera.height", Integer.class), hero);
        camera.addRenderableObject(map);
        PlayerListener playerListener = new PlayerListener(this, hero, map, heroesService);
        this.renderer = new CameraRenderer(camera, playerListener);
        commandsGenerators = new ArrayList<>();
        commandsGenerators.add(playerListener);
    }

    public App(List<ICommandGenerator> commandsGenerators) {
        this.commandsGenerators = commandsGenerators;
    }

    public App(IMap map, IHeroesService heroesService, List<ICommandGenerator> commandsGenerators) {
        this.map = map;
        this.heroesService = heroesService;
        IHero hero = heroesService.getPlayer();
        camera = new Camera(Settings.getProperty("camera.width", Integer.class),
                Settings.getProperty("camera.height", Integer.class), hero);
        camera.addRenderableObject(map);
        PlayerListener playerListener = new PlayerListener(this, hero, map, heroesService);
        this.renderer = new CameraRenderer(camera, playerListener);
        this.commandsGenerators = commandsGenerators;
        commandsGenerators.add(playerListener);
    }

    public void update() {
        List<ICommand> commands = commandsGenerators.stream()
                .map(ICommandGenerator::getCommand)
                .collect(Collectors.toList());
        for (ICommand command : commands) {
            if (command == null) {
                continue;
            }
            List<ICommandGenerator> commandGenerators = command.apply();
            this.commandsGenerators.addAll(commandGenerators);
            this.renderer.render();
        }
        for (IHero h : heroesService.heroes()) {
            camera.addRenderableObject(h);
        }
        this.renderer.render();
        if (heroesService.getPlayer().isDead()) {
            Notification died = new Notification("You died", actionEvent -> System.exit(0));
        }
    }

    public IMap getMap() {
        return map;
    }

    public IHeroesService getHeroesService() {
        return heroesService;
    }

    public List<ICommandGenerator> getCommandsGenerators() {
        return commandsGenerators;
    }
}
