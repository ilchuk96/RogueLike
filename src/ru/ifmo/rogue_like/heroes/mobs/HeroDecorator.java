package ru.ifmo.rogue_like.heroes.mobs;

import ru.ifmo.rogue_like.heroes.MoveAction;
import ru.ifmo.rogue_like.heroes.mobs.move_strategies.IHeroStrategy;
import ru.ifmo.rogue_like.map.IMap;
import ru.ifmo.rogue_like.rendering_system.IView;

public abstract class HeroDecorator implements IHero {

    protected IHero hero;

    public HeroDecorator(IHero hero) {
        this.hero = hero;
    }

    public boolean move(IMap map) {
        return move(map, getMove(map));
    }

    @Override
    abstract public MoveAction getMove(IMap map);

    @Override
    public void setStrategy(IHeroStrategy strategy) {
        hero.setStrategy(strategy);
    }

    @Override
    public IHeroStrategy getStrategy() {
        return hero.getStrategy();
    }


    @Override
    public void getDamage(int damage) {
        hero.getDamage(damage);
    }

    @Override
    public boolean isDead() {
        return hero.isDead();
    }

    @Override
    public int getX() {
        return hero.getX();
    }

    @Override
    public int getY() {
        return hero.getY();
    }

    @Override
    public IView getView(long time) {
        return hero.getView(time);
    }

    abstract public void confuse(int time);
}
