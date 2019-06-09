package ru.ifmo.rogue_like.heroes;

import java.util.List;

import ru.ifmo.rogue_like.heroes.mobs.IHero;

public interface IHeroesService {
    IHero getPlayer();

    IHero getHero(int x, int y);

    void addHero(IHero newHero);

    List<IHero> heroes();
}
