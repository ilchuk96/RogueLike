package ru.ifmo.rogue_like;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ru.ifmo.rogue_like.command_generators.ICommandGenerator;
import ru.ifmo.rogue_like.commands.ICommand;
import ru.ifmo.rogue_like.heroes.HeroesService;
import ru.ifmo.rogue_like.heroes.IHeroesService;
import ru.ifmo.rogue_like.heroes.mobs.Hero;
import ru.ifmo.rogue_like.heroes.mobs.IHero;
import ru.ifmo.rogue_like.heroes.mobs.move_strategies.PlayerStrategy;
import ru.ifmo.rogue_like.heroes.player.PlayerListener;
import ru.ifmo.rogue_like.map.IMap;
import ru.ifmo.rogue_like.map.Map;
import ru.ifmo.rogue_like.rendering_system.CameraRenderer;
import ru.ifmo.rogue_like.rendering_system.camera.Camera;
import ru.ifmo.rogue_like.rendering_system.camera.ICamera;

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

    public App(IMap map, IHeroesService heroesService, CameraRenderer renderer) {
        this.map = map;
        this.heroesService = heroesService;
        this.renderer = renderer;
        this.renderer.render();
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
    }

    public IMap getMap() {
        return map;
    }
}
