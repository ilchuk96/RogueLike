package ru.ifmo.rogue_like.heroes.mobs;

import ru.ifmo.rogue_like.heroes.mobs.move_strategies.IHeroStrategy;
import ru.ifmo.rogue_like.rendering_system.IView;

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
