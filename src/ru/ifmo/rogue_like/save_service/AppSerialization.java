package ru.ifmo.rogue_like.save_service;

import java.util.ArrayList;
import java.util.List;

import ru.ifmo.rogue_like.App;
import ru.ifmo.rogue_like.command_generators.ICommandGenerator;
import ru.ifmo.rogue_like.heroes.IHeroesService;
import ru.ifmo.rogue_like.heroes.PlayerListener;
import ru.ifmo.rogue_like.map.IMap;

public class AppSerialization {
    private final IHeroesService heroesService;
    private final IMap map;
    private final List<ICommandGenerator> commandGeneratorList;

    public AppSerialization(App app) {
        this.heroesService = app.getHeroesService();
        this.map = app.getMap();
        this.commandGeneratorList = new ArrayList<>(app.getCommandsGenerators());
        for (int i = 0; i < commandGeneratorList.size(); i++) {
            if (this.commandGeneratorList.get(i) instanceof PlayerListener) {
                this.commandGeneratorList.remove(i);
                i--;
            }
        }
    }

    public App getApp() {
        return new App(map, heroesService, commandGeneratorList);
    }
}
