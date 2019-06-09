package ru.ifmo.rogue_like.heroes.mobs.move_strategies;

import ru.ifmo.rogue_like.heroes.Magic.Confuse;
import ru.ifmo.rogue_like.heroes.MoveAction;
import ru.ifmo.rogue_like.heroes.mobs.Hero;
import ru.ifmo.rogue_like.heroes.mobs.HeroDecorator;
import ru.ifmo.rogue_like.map.IMap;
import ru.ifmo.rogue_like.map.ISquare;
import ru.ifmo.rogue_like.rendering_system.IView;

import javax.swing.*;
import java.util.List;

public class Passive implements IHeroStrategy {

    private Confuse confuse = new Confuse();

    @Override
    public MoveAction moveDirection(IMap map, int x, int y) {
        List<List<ISquare>> field = map.getField();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (field.get(x + i).get(y + j) instanceof HeroDecorator && (i != 0 || j != 0)) {
                    return new MoveAction(0, 0,1);
                }
            }
        }
        return new MoveAction(0,0, 0);
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

    @Override
    public void castAction(IMap map, Hero hero) {
        confuse.cast(map, hero);
    }
}
