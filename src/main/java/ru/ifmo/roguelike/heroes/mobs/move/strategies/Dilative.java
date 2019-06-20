package ru.ifmo.roguelike.heroes.mobs.move.strategies;

import ru.ifmo.roguelike.heroes.IHeroesService;
import ru.ifmo.roguelike.render.IView;
import ru.ifmo.roguelike.heroes.MoveAction;
import ru.ifmo.roguelike.heroes.mobs.Hero;
import ru.ifmo.roguelike.heroes.mobs.IHero;
import ru.ifmo.roguelike.map.IMap;

import java.util.Random;

/**
 * Dilative mob strategy, run from player if see one, if not became passive
 */
public class Dilative implements IHeroStrategy {
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
                    int dx = - sign(i);
                    int dy = - sign(j);
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
                result[0][0] = 'D';
                return result;
            }
        };
    }

    @Override
    public void castAction(IHeroesService heroesService, Hero hero) {

    }
}
