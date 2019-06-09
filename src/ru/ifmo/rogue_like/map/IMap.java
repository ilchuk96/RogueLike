package ru.ifmo.rogue_like.map;

import java.io.IOException;
import java.util.List;

import ru.ifmo.rogue_like.heroes.MoveAction;
import ru.ifmo.rogue_like.heroes.mobs.HeroDecorator;
import ru.ifmo.rogue_like.rendering_system.IRenderable;

public interface IMap extends IRenderable {
    List<List<ISquare>> getField();

    HeroDecorator updateMap(int x, int y, char direction);

    default HeroDecorator updateMap(int x, int y, MoveAction direction) {
        int xDir = direction.getX();
        int yDir = direction.getY();
        char charDirection = 'w';
        if (xDir == 1) {
            charDirection = 'd';
        }
        if (xDir == -1) {
            charDirection = 'a';
        }
        if (yDir == 1) {
            charDirection = 's';
        }
        return updateMap(x, y, charDirection);
    }

    public int getHeroX();

    public int getHeroY();

    public void addPlayer(HeroDecorator player);

    public void deleteMob(int x, int y);

    public List<HeroDecorator> getHeroes();

    public void move(int x, int y, int dx, int dy);

//    String saveMap();
}
