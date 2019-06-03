package ru.ifmo.rogue_like.heroes.mobs.move_strategies;

import ru.ifmo.rogue_like.heroes.MoveDirection;
import ru.ifmo.rogue_like.map.IMap;
import ru.ifmo.rogue_like.rendering_system.IView;

import java.util.Random;

public class Dilative implements IHeroStrategy {
    private Random random = new Random();

    private int sign(int a) {
        if (a > 0) return 1;
        if (a < 0) return -1;
        return 0;
    }

    @Override
    public MoveDirection moveDirection(IMap map, int x, int y) {
        char[][] field = map.getView(0).getView();
        for (int i = -4; i <= 4; i++) {
            for (int j = -4; j <= 4; j++) {
                if (x + i > 0 && x + i < field.length && y + j > 0 && y + j < field[0].length && field[x+i][y+j] == '$') {
                    int dx = - sign(i);
                    int dy = - sign(j);
                    if (dx != 0 && dy != 0) {
                        if (random.nextInt(2) == 0) {
                            dx = 0;
                        } else {
                            dy = 0;
                        }
                    }
                    return new MoveDirection(dx, dy);
                }
            }
        }
        return new MoveDirection(0, 0);
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
}
