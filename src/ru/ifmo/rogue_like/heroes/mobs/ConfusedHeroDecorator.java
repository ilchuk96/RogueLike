package ru.ifmo.rogue_like.heroes.mobs;

import ru.ifmo.rogue_like.heroes.MoveAction;
import ru.ifmo.rogue_like.map.IMap;

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
    public MoveAction getMove(IMap map) {
        MoveAction move = hero.getMove(map);
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
    public boolean move(IMap map, MoveAction moveDirection) {
        return hero.move(map, moveDirection);
    }

    @Override
    public void confuse(int time) {
        timeLeft += time;
    }
}
