package ru.ifmo.roguelike;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ru.ifmo.roguelike.commands.ICommand;
import ru.ifmo.roguelike.commands.generators.ICommandGenerator;
import ru.ifmo.roguelike.heroes.IHeroesService;
import ru.ifmo.roguelike.heroes.mobs.IHero;
import ru.ifmo.roguelike.render.CameraRenderer;
import ru.ifmo.roguelike.render.camera.ICamera;

public class CommandsExecutor implements Runnable {
    private List<ICommandGenerator> commandsGenerators;
    private CameraRenderer renderer;
    private IHeroesService heroesService;
    private ICamera camera;

    public CommandsExecutor(List<ICommandGenerator> commandsGenerators,
                            CameraRenderer renderer,
                            IHeroesService heroesService,
                            ICamera camera) {
        this.commandsGenerators = commandsGenerators;
        this.renderer = renderer;
        this.heroesService = heroesService;
        this.camera = camera;
        for (IHero h : heroesService.heroes()) {
            camera.addRenderableObject(h);
        }
        this.renderer.render();
    }

    private void update() {
        List<ICommandGenerator> newCommandGenerators = new ArrayList<>();
        for (ICommandGenerator commandGenerator : commandsGenerators) {
            newCommandGenerators.addAll(applyCommand(commandGenerator));
            this.renderer.render();
        }
        this.commandsGenerators.addAll(newCommandGenerators);
        for (IHero h : heroesService.heroes()) {
            camera.addRenderableObject(h);
        }
        this.renderer.render();
        if (heroesService.getPlayer().isDead()) {
            File save = new File(Settings.getProperty("save.file", String.class));
            if (save.isFile()) {
                save.delete();
            }
            Notification notification = new Notification("you died", actionEvent -> System.exit(0));
        }
    }

    private synchronized List<ICommandGenerator> applyCommand(ICommandGenerator commandGenerator) {
        while (!commandGenerator.isReady()) {
            try {
                wait(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        ICommand command = commandGenerator.getCommand();
        return command.apply();
    }

    @Override
    public void run() {
        while (true) {
            update();
        }
    }
}
