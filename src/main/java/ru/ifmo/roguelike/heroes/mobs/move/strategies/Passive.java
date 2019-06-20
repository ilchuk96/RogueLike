package ru.ifmo.roguelike.heroes.mobs.move.strategies;

import java.util.List;

import ru.ifmo.roguelike.heroes.IHeroesService;
import ru.ifmo.roguelike.heroes.mobs.IHero;
import ru.ifmo.roguelike.render.IView;
import ru.ifmo.roguelike.heroes.MoveAction;
import ru.ifmo.roguelike.heroes.magic.Confuse;
import ru.ifmo.roguelike.heroes.mobs.Hero;
import ru.ifmo.roguelike.heroes.mobs.HeroDecorator;
import ru.ifmo.roguelike.map.IMap;
import ru.ifmo.roguelike.map.ISquare;

/**
 * Passive mob strategy, do nothing if see anyone cast confusion
 */
public class Passive implements IHeroStrategy {

    private Confuse confuse = new Confuse();

    @Override
    public MoveAction moveDirection(IMap map, IHeroesService heroesService, IHero hero) {
        int x = hero.getX();
        int y = hero.getY();
        List<List<ISquare>> field = map.getField();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (field.get(x + i).get(y + j) instanceof HeroDecorator && (i != 0 || j != 0)) {
                    return new MoveAction(0, 0, 1);
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
                result[0][0] = 'P';
                return result;
            }
        };
    }

    @Override
    public void castAction(IHeroesService heroesService, Hero hero) {
        confuse.cast(heroesService, hero);
    }
}
