package ru.ifmo.rogue_like.heroes.mobs;

import ru.ifmo.rogue_like.heroes.MoveDirection;
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
    public MoveDirection getMove(IMap map) {
        MoveDirection move = hero.getMove(map);
        if (timeLeft > 0) {
            int choice = random.nextInt(3) - 1;
            if (choice != 0) {
                if (move.getX() != 0) {
                    move = new MoveDirection(0, choice);
                } else {
                    move = new MoveDirection(choice, 0);
                }
            }
            timeLeft--;
        }
        return move;
    }

    @Override
    public boolean move(IMap map, MoveDirection moveDirection) {
        return hero.move(map, moveDirection);
    }

    @Override
    public void confuse(int time) {
        timeLeft += time;
    }
}
