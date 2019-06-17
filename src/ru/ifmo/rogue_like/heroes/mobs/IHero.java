package ru.ifmo.rogue_like.heroes.mobs;

import ru.ifmo.rogue_like.heroes.IHeroesService;
import ru.ifmo.rogue_like.heroes.MoveAction;
import ru.ifmo.rogue_like.heroes.mobs.move_strategies.IHeroStrategy;
import ru.ifmo.rogue_like.map.IMap;
import ru.ifmo.rogue_like.map.IPositionable;
import ru.ifmo.rogue_like.rendering_system.IRenderable;

public interface IHero extends IRenderable, IPositionable {
    void setStrategy(IHeroStrategy strategy);

    IHeroStrategy getStrategy();

    boolean move(IHeroesService heroesService, MoveAction moveDirection);

    MoveAction getMove(IHeroesService heroesService, IMap map);

    void changeHPBy(int damage);

    int getMana();

    void reduceMana(int m);

    int getExp();

    void changeExpBy(int e);

    boolean isDead();
}