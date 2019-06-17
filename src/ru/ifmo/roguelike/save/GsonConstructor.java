package ru.ifmo.roguelike.save;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.ifmo.roguelike.commands.generators.ICommandGenerator;
import ru.ifmo.roguelike.heroes.IHeroesService;
import ru.ifmo.roguelike.heroes.magic.Magic;
import ru.ifmo.roguelike.heroes.mobs.HeroDecorator;
import ru.ifmo.roguelike.heroes.mobs.IHero;
import ru.ifmo.roguelike.heroes.mobs.move.strategies.IHeroStrategy;
import ru.ifmo.roguelike.map.IMap;
import ru.ifmo.roguelike.map.IPositionable;
import ru.ifmo.roguelike.map.ISquare;

public class GsonConstructor {

    public static Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ISquare.class, new InterfaceAdapter<>());
        gsonBuilder.registerTypeAdapter(IMap.class, new InterfaceAdapter<>());
        gsonBuilder.registerTypeAdapter(IHero.class, new InterfaceAdapter<>());
        gsonBuilder.registerTypeAdapter(IHeroStrategy.class, new InterfaceAdapter<>());
        gsonBuilder.registerTypeAdapter(IPositionable.class, new InterfaceAdapter<>());
        gsonBuilder.registerTypeAdapter(HeroDecorator.class, new InterfaceAdapter<>());
        gsonBuilder.registerTypeAdapter(Magic.class, new InterfaceAdapter<>());
        gsonBuilder.registerTypeAdapter(IHeroesService.class, new InterfaceAdapter<>());
        gsonBuilder.registerTypeAdapter(ICommandGenerator.class, new InterfaceAdapter<>());
        return gsonBuilder.create();
    }
}
