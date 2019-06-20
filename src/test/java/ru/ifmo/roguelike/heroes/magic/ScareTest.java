package ru.ifmo.roguelike.heroes.magic;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import ru.ifmo.roguelike.heroes.IHeroesService;
import ru.ifmo.roguelike.heroes.mobs.IHero;
import ru.ifmo.roguelike.heroes.mobs.move.strategies.Dilative;
import ru.ifmo.roguelike.map.IMap;

import static org.mockito.Mockito.*;
import static ru.ifmo.roguelike.heroes.magic.HeroesMockGenerator.generateHeroesInSquare;

public class ScareTest {
    private IHero hero;
    private IMap map;
    private IHeroesService heroesService;

    @Before
    public void setUp() {
        hero = mock(IHero.class);
        map = mock(IMap.class);
        heroesService = mock(IHeroesService.class);
    }

    @Test
    public void testScareAppliesAtAllHeroesInSquare() {
        Scare scareMagic = new Scare();
        when(hero.getX()).thenReturn(10);
        when(hero.getY()).thenReturn(10);
        List<IHero> heroesInSquare = generateHeroesInSquare(10, 10, 1);
        for (IHero hero : heroesInSquare) {
            when(heroesService.getHero(hero.getX(), hero.getY())).thenReturn(hero);
        }
        scareMagic.apply(heroesService, hero);
        for (IHero hero : heroesInSquare) {
            verify(hero).setStrategy(any(Dilative.class));
        }
    }

    @Test
    public void testScareDoesntAppliesOnPlayer() {
        Scare scareMagic = new Scare();
        when(hero.getX()).thenReturn(10);
        when(hero.getY()).thenReturn(10);
        scareMagic.apply(heroesService, hero);
        verify(hero, times(0)).setStrategy(any(Dilative.class));
    }

}
