package ru.ifmo.roguelike.heroes.mobs.move.strategies;

import ru.ifmo.roguelike.heroes.IHeroesService;
import ru.ifmo.roguelike.render.IView;
import ru.ifmo.roguelike.heroes.MoveAction;
import ru.ifmo.roguelike.heroes.mobs.Hero;
import ru.ifmo.roguelike.heroes.mobs.IHero;
import ru.ifmo.roguelike.map.IMap;

public interface IHeroStrategy {
    MoveAction moveDirection(IMap map, IHeroesService heroesService, IHero hero);

    IView getHeroView(int x, int y);

    default void castAction(IHeroesService heroesService, Hero hero) {
    }
}
