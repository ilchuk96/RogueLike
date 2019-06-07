package ru.ifmo.rogue_like.heroes.mobs;

import ru.ifmo.rogue_like.heroes.MoveDirection;
import ru.ifmo.rogue_like.heroes.mobs.move_strategies.IHeroStrategy;
import ru.ifmo.rogue_like.map.IMap;
import ru.ifmo.rogue_like.map.IPositionable;
import ru.ifmo.rogue_like.map.ISquare;
import ru.ifmo.rogue_like.rendering_system.IRenderable;

public interface IHero extends IRenderable, IPositionable, ISquare {

    MoveDirection getMove(IMap map);

    void setStrategy(IHeroStrategy strategy);

    boolean move(IMap map, MoveDirection moveDirection);

    void getDamage(int damage);

    boolean isDead();
}