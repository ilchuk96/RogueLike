package ru.ifmo.roguelike.heroes.mobs;

import ru.ifmo.roguelike.heroes.IHeroesService;
import ru.ifmo.roguelike.map.IMap;
import ru.ifmo.roguelike.heroes.MoveAction;

import java.util.Random;

public class ConfusedHeroDecorator extends HeroDecorator {

    private int timeLeft;
    private Random random;

    public ConfusedHeroDecorator(IHero hero) {
        super(hero);
        timeLeft = 0;
        random = new Random();
    }

    @Override
    public void confuse(int time) {
        timeLeft += time;
    }

    @Override
    public MoveAction getMove(IHeroesService heroesService, IMap map) {
        MoveAction move = hero.getMove(heroesService, map);
        if (timeLeft > 0) {
            int choice = random.nextInt(3) - 1;
            if (choice != 0) {
                if (move.getX() != 0) {
                    move = new MoveAction(0, choice, 0);
                } else {
                    move = new MoveAction(choice, 0, 0);
                }
            }
            timeLeft--;
        }
        return move;

    }

    @Override
    public boolean move(IHeroesService heroesService, MoveAction moveDirection) {
        return hero.move(heroesService, moveDirection);
    }
}
