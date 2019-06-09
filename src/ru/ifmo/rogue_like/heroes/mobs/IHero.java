package ru.ifmo.rogue_like.heroes.mobs;

import ru.ifmo.rogue_like.heroes.MoveAction;
import ru.ifmo.rogue_like.heroes.mobs.move_strategies.IHeroStrategy;
import ru.ifmo.rogue_like.map.IMap;
import ru.ifmo.rogue_like.map.IPositionable;
import ru.ifmo.rogue_like.map.ISquare;
import ru.ifmo.rogue_like.rendering_system.IRenderable;

public interface IHero extends IRenderable, IPositionable, ISquare {

    MoveAction getMove(IMap map);

    void setStrategy(IHeroStrategy strategy);

    IHeroStrategy getStrategy();

    boolean move(IMap map, MoveAction moveDirection);

    void getDamage(int damage);

    boolean isDead();
}