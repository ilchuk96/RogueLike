package ru.ifmo.rogue_like.heroes.mobs.move_strategies;

import ru.ifmo.rogue_like.heroes.Magic.MagicInventory;
import ru.ifmo.rogue_like.heroes.MoveAction;
import ru.ifmo.rogue_like.heroes.mobs.Hero;
import ru.ifmo.rogue_like.map.IMap;
import ru.ifmo.rogue_like.rendering_system.IView;

public class PlayerStrategy implements IHeroStrategy {

    public MagicInventory magics;

    private MoveAction action;

    public PlayerStrategy() {
        magics = new MagicInventory();
        action = new MoveAction(0, 0,0);
    }

    public void setAction(MoveAction action) {
        this.action = action;
    }

    @Override
    public MoveAction moveDirection(IMap map, int x, int y) {
        return action;
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
                result[0][0] = '$';
                return result;
            }
        };
    }

    @Override
    public void castAction(IMap map, Hero hero) {
        magics.cast(map, hero);
    }
}
