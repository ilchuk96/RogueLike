package ru.ifmo.roguelike.heroes;

import org.junit.Test;
import ru.ifmo.roguelike.heroes.mobs.IHero;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HeroesServiceTest {
    @Test
    public void testGetPlayerReturnsPlayer() {
        IHero player = mock(IHero.class);
        HeroesService heroesService = new HeroesService(player);
        assertEquals(player, heroesService.getPlayer());
    }

    @Test
    public void testGetHeroByCoorReturnsHeroIfExists() {
        IHero player = mock(IHero.class);
        HeroesService heroesService = new HeroesService(player);
        IHero newHero = mock(IHero.class);
        when(newHero.getX()).thenReturn(10);
        when(newHero.getY()).thenReturn(10);
        heroesService.addHero(newHero);
        assertEquals(newHero, heroesService.getHero(10, 10));
    }

    @Test
    public void testGetHeroByCoorDoesntReturnHeroIfNotExists() {
        IHero player = mock(IHero.class);
        HeroesService heroesService = new HeroesService(player);
        IHero newHero = mock(IHero.class);
        when(newHero.getX()).thenReturn(10);
        when(newHero.getY()).thenReturn(10);
        heroesService.addHero(newHero);
        assertNull(heroesService.getHero(10, 11));
    }


    @Test
    public void testHeroAdditionAddsHero() {
        IHero player = mock(IHero.class);
        HeroesService heroesService = new HeroesService(player);
        IHero newHero = mock(IHero.class);
        heroesService.addHero(newHero);
        assertTrue(heroesService.heroes().contains(newHero));
    }

    @Test
    public void testHeroesServiceHeroContainsPlayer() {
        IHero player = mock(IHero.class);
        HeroesService heroesService = new HeroesService(player);
        IHero newHero = mock(IHero.class);
        heroesService.addHero(newHero);
        assertTrue(heroesService.heroes().contains(player));
    }

    @Test
    public void testHeroesServiceGetByCoorRemovesDeadHeroes() {
        IHero player = mock(IHero.class);
        HeroesService heroesService = new HeroesService(player);
        IHero newHero = mock(IHero.class);
        when(newHero.getX()).thenReturn(10);
        when(newHero.getY()).thenReturn(10);
        when(newHero.isDead()).thenReturn(true);
        heroesService.addHero(newHero);
        assertNull(heroesService.getHero(10, 10));
    }

    @Test
    public void testHeroesServiceHeroesRemovesDeadHeroes() {
        IHero player = mock(IHero.class);
        HeroesService heroesService = new HeroesService(player);
        IHero newHero = mock(IHero.class);
        when(newHero.getX()).thenReturn(10);
        when(newHero.getY()).thenReturn(10);
        when(newHero.isDead()).thenReturn(true);
        heroesService.addHero(newHero);
        assertFalse(heroesService.heroes().contains(newHero));
    }
}
