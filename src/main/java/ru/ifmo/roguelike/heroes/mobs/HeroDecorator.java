package ru.ifmo.roguelike.heroes.mobs;

import ru.ifmo.roguelike.heroes.mobs.move.strategies.IHeroStrategy;
import ru.ifmo.roguelike.render.IView;

public abstract class HeroDecorator implements IHero {

    protected IHero hero;

    public HeroDecorator(IHero hero) {
        this.hero = hero;
    }

    @Override
    public void setStrategy(IHeroStrategy strategy) {
        hero.setStrategy(strategy);
    }

    @Override
    public IHeroStrategy getStrategy() {
        return hero.getStrategy();
    }

    @Override
    public void changeHPBy(int damage) {
        hero.changeHPBy(damage);
    }

    @Override
    public int getMana() {
        return  hero.getMana();
    }

    @Override
    public void reduceMana(int m) {
        hero.reduceMana(m);
    }

    @Override
    public int getExp() {
        return hero.getExp();
    }

    @Override
    public void changeExpBy(int e) {
        hero.changeExpBy(e);
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
