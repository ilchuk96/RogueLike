package ru.ifmo.roguelike.heroes.magic;

import java.util.ArrayList;
import java.util.List;

import ru.ifmo.roguelike.heroes.mobs.IHero;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HeroesMockGenerator {
    public static List<IHero> generateHeroesInSquare(int x, int y, int size) {
        java.util.List<ru.ifmo.roguelike.heroes.mobs.IHero> resultHeroesList = new ArrayList<>();
        for (int i = -size; i <= size; i++) {
            for (int j = -size; j <= size; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                ru.ifmo.roguelike.heroes.mobs.IHero target = mock(ru.ifmo.roguelike.heroes.mobs.IHero.class);
                when(target.getX()).thenReturn(x + i);
                when(target.getY()).thenReturn(y + j);
                resultHeroesList.add(target);
            }
        }
        return resultHeroesList;
    }

}
