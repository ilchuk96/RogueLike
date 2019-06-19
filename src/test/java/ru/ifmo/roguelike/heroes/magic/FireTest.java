package ru.ifmo.roguelike.heroes.magic;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import ru.ifmo.roguelike.heroes.IHeroesService;
import ru.ifmo.roguelike.heroes.mobs.IHero;
import ru.ifmo.roguelike.map.IMap;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static ru.ifmo.roguelike.heroes.magic.HeroesMockGenerator.generateHeroesInSquare;

public class FireTest {
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
    public void testFireAppliesAtAllHeroesInSquare() {
        Fire fireMagic = new Fire();
        when(hero.getX()).thenReturn(10);
        when(hero.getY()).thenReturn(10);
        List<IHero> heroesInSquare = generateHeroesInSquare(10, 10, 3);
        for (IHero hero : heroesInSquare) {
            when(heroesService.getHero(hero.getX(), hero.getY())).thenReturn(hero);
        }
        fireMagic.apply(heroesService, hero);
        for (IHero hero : heroesInSquare) {
            verify(hero).changeHPBy(anyInt());
        }
    }

    @Test
    public void testFireDoesntAppliesOnPlayer() {
        Fire fireMagic = new Fire();
        when(hero.getX()).thenReturn(10);
        when(hero.getY()).thenReturn(10);
        fireMagic.apply(heroesService, hero);
        verify(hero, times(0)).changeHPBy(anyInt());
    }

    @Test
    public void testFireIncreaseExpToPlayerWithCountOfDeadPlayers() {
        Fire fireMagic = new Fire();
        when(hero.getX()).thenReturn(10);
        when(hero.getY()).thenReturn(10);
        List<IHero> heroesInSquare = generateHeroesInSquare(10, 10, 3);
        for (IHero hero : heroesInSquare) {
            when(hero.isDead()).thenReturn(true);
            when(heroesService.getHero(hero.getX(), hero.getY())).thenReturn(hero);
        }
        fireMagic.apply(heroesService, hero);
        verify(hero, times(heroesInSquare.size())).changeExpBy(anyInt());
    }

    @Test
    public void testFireIncreaseDamageAfterUpdate() {
        Fire fireMagic = new Fire();
        when(hero.getX()).thenReturn(10);
        when(hero.getY()).thenReturn(10);

        // Before upgrade

        List<IHero> heroesInSquare = generateHeroesInSquare(10, 10, 3);
        int damageBefore[] = new int[1];
        for (IHero hero : heroesInSquare) {
            when(hero.isDead()).thenReturn(true);
            doAnswer(a -> {
                damageBefore[0] = -(int) a.getArgument(0);
                return null;
            }).when(hero).changeHPBy(anyInt());
            when(heroesService.getHero(hero.getX(), hero.getY())).thenReturn(hero);
        }
        fireMagic.apply(heroesService, hero);

        // After upgrage
        heroesInSquare = generateHeroesInSquare(10, 10, 3);
        fireMagic.upgrade();
        int damageAfter[] = new int[1];
        for (IHero hero : heroesInSquare) {
            when(hero.isDead()).thenReturn(true);
            doAnswer(a -> {
                damageAfter[0] = -(int) a.getArgument(0);
                return null;
            }).when(hero).changeHPBy(anyInt());
            when(heroesService.getHero(hero.getX(), hero.getY())).thenReturn(hero);
        }
        fireMagic.apply(heroesService, hero);
        assertTrue(damageAfter[0] > damageBefore[0]);
    }
}
