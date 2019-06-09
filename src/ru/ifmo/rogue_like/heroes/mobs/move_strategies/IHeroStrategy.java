package ru.ifmo.rogue_like.heroes.mobs.move_strategies;

import ru.ifmo.rogue_like.heroes.IHeroesService;
import ru.ifmo.rogue_like.heroes.MoveAction;
import ru.ifmo.rogue_like.heroes.mobs.Hero;
import ru.ifmo.rogue_like.heroes.mobs.IHero;
import ru.ifmo.rogue_like.map.IMap;
import ru.ifmo.rogue_like.rendering_system.IView;

public interface IHeroStrategy {
    MoveAction moveDirection(IMap map, IHeroesService heroesService, IHero hero);

    IView getHeroView(int x, int y);

    default void castAction(IHeroesService heroesService, Hero hero) {
    }
}
