package ru.ifmo.roguelike.heroes.magic;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import ru.ifmo.roguelike.heroes.IHeroesService;
import ru.ifmo.roguelike.heroes.mobs.IHero;
import ru.ifmo.roguelike.map.IMap;

import static org.mockito.Mockito.*;
import static ru.ifmo.roguelike.heroes.magic.HeroesMockGenerator.generateHeroesInSquare;

public class HealTest {
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
    public void testHealDoesntAppliesAtHeroesInSquare() {
        Heal healMagic = new Heal();
        when(hero.getX()).thenReturn(10);
        when(hero.getY()).thenReturn(10);
        List<IHero> heroesInSquare = generateHeroesInSquare(10, 10, 10);
        for (IHero hero : heroesInSquare) {
            when(heroesService.getHero(hero.getX(), hero.getY())).thenReturn(hero);
        }
        healMagic.apply(heroesService, hero);
        for (IHero hero : heroesInSquare) {
            verify(hero, times(0)).changeHPBy(anyInt());
        }
    }

    @Test
    public void testHealIncreasesPlayerHP() {
        Heal healMagic = new Heal();
        when(hero.getX()).thenReturn(10);
        when(hero.getY()).thenReturn(10);
        healMagic.apply(heroesService, hero);
        verify(hero, times(1)).changeHPBy(anyInt());
    }
}
