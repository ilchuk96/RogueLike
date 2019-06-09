package ru.ifmo.rogue_like.heroes.mobs.move_strategies;

import java.util.List;

import ru.ifmo.rogue_like.heroes.HeroesService;
import ru.ifmo.rogue_like.heroes.IHeroesService;
import ru.ifmo.rogue_like.heroes.MoveAction;
import ru.ifmo.rogue_like.heroes.magic.Confuse;
import ru.ifmo.rogue_like.heroes.mobs.Hero;
import ru.ifmo.rogue_like.heroes.mobs.HeroDecorator;
import ru.ifmo.rogue_like.heroes.mobs.IHero;
import ru.ifmo.rogue_like.map.IMap;
import ru.ifmo.rogue_like.map.ISquare;
import ru.ifmo.rogue_like.rendering_system.IView;

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
