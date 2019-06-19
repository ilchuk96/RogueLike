package ru.ifmo.roguelike.commands;

import org.junit.Before;
import org.junit.Test;
import ru.ifmo.roguelike.heroes.IHeroesService;
import ru.ifmo.roguelike.heroes.MoveAction;
import ru.ifmo.roguelike.heroes.mobs.IHero;
import ru.ifmo.roguelike.map.IMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class HeroMoveCommandTest {
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
    public void testSameMoveActionApplied() {
        MoveAction action = mock(MoveAction.class);
        when(map.isEmpty(anyInt(), anyInt())).thenReturn(true);
        HeroMoveCommand command = new HeroMoveCommand(hero, map, heroesService, action);
        command.apply();
        verify(hero).move(heroesService, action);
    }

    @Test
    public void testNoMoveIfMapHasTileHere() {
        MoveAction action = mock(MoveAction.class);
        when(map.isEmpty(anyInt(), anyInt())).thenReturn(false);
        HeroMoveCommand command = new HeroMoveCommand(hero, map, heroesService, action);
        command.apply();
        verify(hero, times(0)).move(heroesService, action);
    }

    @Test
    public void testNewCommandGeneratorsReturnsIfMapGenerateHeroes() {
        MoveAction action = mock(MoveAction.class);
        when(map.isEmpty(anyInt(), anyInt())).thenReturn(false);
        when(map.updateMap(anyInt(), anyInt(), any())).thenReturn(mock(IHero.class));
        HeroMoveCommand command = new HeroMoveCommand(hero, map, heroesService, action);
        assertTrue(command.apply().size() > 0);
    }

    @Test
    public void testEmptyListReturnsIfMapDoesntGenerateHeroes() {
        MoveAction action = mock(MoveAction.class);
        HeroMoveCommand command = new HeroMoveCommand(hero, map, heroesService, action);
        assertEquals(0, command.apply().size());
    }
}
