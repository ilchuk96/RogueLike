package ru.ifmo.roguelike.commands.generators;

import org.junit.Before;
import org.junit.Test;
import ru.ifmo.roguelike.commands.ICommand;
import ru.ifmo.roguelike.heroes.IHeroesService;
import ru.ifmo.roguelike.heroes.MoveAction;
import ru.ifmo.roguelike.heroes.mobs.IHero;
import ru.ifmo.roguelike.map.IMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class HeroCommandGeneratorTest {
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
    public void testHeroCommandGeneratorGetsMoveFromHero() {
        HeroCommandGenerator heroCommandGenerator = new HeroCommandGenerator(hero, map, heroesService);
        heroCommandGenerator.getCommand();
        verify(hero).getMove(heroesService, map);
    }

    @Test
    public void testHeroCommandGeneratorReturnsActionWithHeroesMove() {
        MoveAction moveAction = mock(MoveAction.class);
        when(hero.getMove(any(), any())).thenReturn(moveAction);
        HeroCommandGenerator heroCommandGenerator = new HeroCommandGenerator(hero, map, heroesService);
        ICommand command = heroCommandGenerator.getCommand();
        command.apply();
        verify(moveAction).getX();
        verify(moveAction).getY();
    }

    @Test
    public void testZeroCommandIfHeroDead() {
        when(hero.isDead()).thenReturn(true);
        when(map.isEmpty(anyInt(), anyInt())).thenReturn(true);
        HeroCommandGenerator heroCommandGenerator = new HeroCommandGenerator(hero, map, heroesService);
        ICommand command = heroCommandGenerator.getCommand();
        command.apply();
        verify(hero).move(eq(heroesService), refEq(new MoveAction(0, 0, 0)));
    }
}
