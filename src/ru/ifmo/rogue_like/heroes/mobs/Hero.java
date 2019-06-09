package ru.ifmo.rogue_like.heroes.mobs;

import ru.ifmo.rogue_like.heroes.IHeroesService;
import ru.ifmo.rogue_like.heroes.MoveAction;
import ru.ifmo.rogue_like.heroes.mobs.move_strategies.Dilative;
import ru.ifmo.rogue_like.heroes.mobs.move_strategies.IHeroStrategy;
import ru.ifmo.rogue_like.heroes.mobs.move_strategies.Passive;
import ru.ifmo.rogue_like.heroes.mobs.move_strategies.PlayerStrategy;
import ru.ifmo.rogue_like.map.IMap;
import ru.ifmo.rogue_like.rendering_system.IView;

public class Hero implements IHero {
    final int MAX_HP = 5;
    final int MAX_MANA = 10;

    private IHeroStrategy strategy;
    private int x;
    private int y;
    private int hp;
    private int mana;
    private int exp;

    public Hero(IHeroStrategy heroStrategy, int x, int y) {
        this.strategy = heroStrategy;
        this.x = x;
        this.y = y;
        hp = 5;
        exp = 0;
        mana = 10;
    }

    @Override
    public MoveAction getMove(IHeroesService heroesService, IMap map) {
        MoveAction moveDirection = strategy.moveDirection(map, heroesService, this);
        if (strategy instanceof Dilative && moveDirection.getX() == 0 && moveDirection.getY() == 0) {
            setStrategy(new Passive());
        }
        return moveDirection;
    }

    @Override
    public void setStrategy(IHeroStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public IHeroStrategy getStrategy() {
        return strategy;
    }


    private void restoreMana() {
        if (mana < MAX_MANA) {
            mana++;
        }
    }


    @Override
    public boolean move(IHeroesService heroesService, MoveAction moveDirection) {
        if (strategy instanceof PlayerStrategy) {
            System.out.println(mana);
        }
        if (moveDirection == null) {
            return false;
        }
        if (moveDirection.getX() == 0 && moveDirection.getY() == 0) {
            if (moveDirection.getType() != 0) {
                strategy.castAction(heroesService, this);
            } else {
                restoreMana();
            }
            return false;
        }
        restoreMana();
        int newX = x + moveDirection.getX();
        int newY = y + moveDirection.getY();
        IHero heroOnTheWay = heroesService.getHero(newX, newY);
        if (heroOnTheWay != null) {
            heroOnTheWay.getDamage(2);
            if (heroOnTheWay.isDead()) {
                exp++;
            }
            return false;
        }
        x += newX;
        y += newY;
        return true;
    }

    public void getDamage(int damage) {
        hp -= damage;
        if (hp == 1 && !(strategy instanceof PlayerStrategy)) {
            setStrategy(new Dilative());
        }
        if (hp > MAX_HP) {
            hp = MAX_HP;
        }
    }

    public int getMana() {
        return mana;
    }

    public void reduceMana(int m) {
        mana -= m;
        if (mana < 0) {
            mana = 0;
        }
    }

    public int getExp() {
        return mana;
    }

    public void reduceExp(int e) {
        exp -= e;
    }

    @Override
    public IView getView(long time) {
        return strategy.getHeroView(x, y);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    /*
    EXAMPLE
     */
    public boolean isDead() {
        return hp <= 0;
    }
}
