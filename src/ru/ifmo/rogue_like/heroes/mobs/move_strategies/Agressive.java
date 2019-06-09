package ru.ifmo.rogue_like.heroes.mobs.move_strategies;

import java.util.Random;

import ru.ifmo.rogue_like.heroes.IHeroesService;
import ru.ifmo.rogue_like.heroes.MoveAction;
import ru.ifmo.rogue_like.heroes.mobs.Hero;
import ru.ifmo.rogue_like.heroes.mobs.IHero;
import ru.ifmo.rogue_like.map.IMap;
import ru.ifmo.rogue_like.rendering_system.IView;

public class Agressive implements IHeroStrategy {

    private Random random = new Random();

    private int sign(int a) {
        if (a > 0) return 1;
        if (a < 0) return -1;
        return 0;
    }

    @Override
    public MoveAction moveDirection(IMap map, IHeroesService heroesService, IHero hero) {
        int x = hero.getX();
        int y = hero.getY();
        IHero player = heroesService.getPlayer();
        int playerX = player.getX();
        int playerY = player.getY();
        for (int i = -4; i <= 4; i++) {
            for (int j = -4; j <= 4; j++) {
                if (x + i == playerX && y + j == playerY) {
                    int dx = sign(i);
                    int dy = sign(j);
                    if (dx != 0 && dy != 0) {
                        if (random.nextInt(2) == 0) {
                            dx = 0;
                        } else {
                            dy = 0;
                        }
                    }
                    return new MoveAction(dx, dy, 0);
                }
            }
        }
        return new MoveAction(0, 0, 0);
    }

    @Override
    public IView getHeroView(int x, int y) {
        return new IView() {
            @Override
            public int getX() {
                return x;
            }

            @Override
            public int getY() {
                return y;
            }

            @Override
            public char[][] getView() {
                char[][] result = new char[1][1];
                result[0][0] = 'A';
                return result;
            }
        };
    }

    @Override
    public void castAction(IHeroesService heroesService, Hero hero) {}
}
