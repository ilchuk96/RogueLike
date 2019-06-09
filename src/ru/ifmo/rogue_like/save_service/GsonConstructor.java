package ru.ifmo.rogue_like.save_service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.ifmo.rogue_like.heroes.mobs.HeroDecorator;
import ru.ifmo.rogue_like.heroes.mobs.IHero;
import ru.ifmo.rogue_like.heroes.mobs.move_strategies.IHeroStrategy;
import ru.ifmo.rogue_like.map.IMap;
import ru.ifmo.rogue_like.map.IPositionable;
import ru.ifmo.rogue_like.map.ISquare;

public class GsonConstructor {

    public static Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ISquare.class, new InterfaceAdapter<>());
        gsonBuilder.registerTypeAdapter(IMap.class, new InterfaceAdapter<>());
        gsonBuilder.registerTypeAdapter(IHero.class, new InterfaceAdapter<>());
        gsonBuilder.registerTypeAdapter(IHeroStrategy.class, new InterfaceAdapter<>());
        gsonBuilder.registerTypeAdapter(IPositionable.class, new InterfaceAdapter<>());
        gsonBuilder.registerTypeAdapter(HeroDecorator.class, new InterfaceAdapter<>());
        return gsonBuilder.create();
    }
}
