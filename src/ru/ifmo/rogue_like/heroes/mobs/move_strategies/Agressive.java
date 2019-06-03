package ru.ifmo.rogue_like.heroes.mobs.move_strategies;

import ru.ifmo.rogue_like.heroes.MoveDirection;
import ru.ifmo.rogue_like.map.IMap;
import ru.ifmo.rogue_like.rendering_system.IView;

public class Agressive implements IHeroStrategy {

    private int sign(int a) {
        if (a > 0) return 1;
        if (a < 0) return -1;
        return 0;
    }

    @Override
    public MoveDirection moveDirection(IMap map, int x, int y) {
        x = sign(x);
        y = sign(y);
        return new MoveDirection(-x, -y);
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
}
