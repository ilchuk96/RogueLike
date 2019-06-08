package ru.ifmo.rogue_like.heroes.mobs.move_strategies;

import ru.ifmo.rogue_like.heroes.MoveAction;
import ru.ifmo.rogue_like.heroes.mobs.HeroDecorator;
import ru.ifmo.rogue_like.map.IMap;
import ru.ifmo.rogue_like.map.ISquare;
import ru.ifmo.rogue_like.rendering_system.IView;

import java.util.List;

public class Passive implements IHeroStrategy {

    private int timeLeft;

    @Override
    public MoveAction moveDirection(IMap map, int x, int y) {
        List<List<ISquare>> field = map.getField();
        if (timeLeft == 0) {
            for (int i = -1; i <= 1; i += 2) {
                for (int j = -1; j <= 1; j += 2) {
                    if (field.get(x + i).get(y + j) instanceof HeroDecorator) {
                        ((HeroDecorator) field.get(x + i).get(y + j)).confuse(5);
                        timeLeft += 10;
                    }
                }
            }
        } else {
            timeLeft--;
        }
        return new MoveAction(0,0, 1);
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
                result[0][0] = 'P';
                return result;
            }
        };
    }
}
