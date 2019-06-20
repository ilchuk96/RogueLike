package ru.ifmo.roguelike.heroes;

import java.util.List;

import ru.ifmo.roguelike.heroes.mobs.IHero;

/**
 * stores and returnees heroes by coordinates
 */
public interface IHeroesService {
    IHero getPlayer();

    IHero getHero(int x, int y);

    void addHero(IHero newHero);

    List<IHero> heroes();
}
