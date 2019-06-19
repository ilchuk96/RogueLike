package ru.ifmo.roguelike.save;

import java.util.ArrayList;
import java.util.List;

import ru.ifmo.roguelike.commands.generators.ICommandGenerator;
import ru.ifmo.roguelike.heroes.IHeroesService;
import ru.ifmo.roguelike.App;
import ru.ifmo.roguelike.heroes.PlayerListener;
import ru.ifmo.roguelike.map.IMap;

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
