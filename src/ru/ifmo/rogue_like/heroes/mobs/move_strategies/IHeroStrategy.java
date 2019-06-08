package ru.ifmo.rogue_like.heroes.mobs.move_strategies;

import ru.ifmo.rogue_like.heroes.MoveAction;
import ru.ifmo.rogue_like.map.IMap;
import ru.ifmo.rogue_like.rendering_system.IView;

public interface IHeroStrategy {
    MoveAction moveDirection(IMap map, int x, int y);

    IView getHeroView(int x, int y);
}
