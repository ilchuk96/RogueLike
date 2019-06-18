package ru.ifmo.roguelike.heroes.mobs;

import ru.ifmo.roguelike.heroes.IHeroesService;
import ru.ifmo.roguelike.heroes.mobs.move.strategies.IHeroStrategy;
import ru.ifmo.roguelike.render.IRenderable;
import ru.ifmo.roguelike.heroes.MoveAction;
import ru.ifmo.roguelike.map.IMap;
import ru.ifmo.roguelike.map.IPositionable;

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