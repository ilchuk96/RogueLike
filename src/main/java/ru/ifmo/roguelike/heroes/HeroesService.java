package ru.ifmo.roguelike.heroes;

import java.util.ArrayList;
import java.util.List;

import ru.ifmo.roguelike.heroes.mobs.IHero;

/**
 * stores and returnees heroes by coordinates
 */
public class HeroesService implements IHeroesService {
    private List<IHero> heroes;
    private IHero player;

    public HeroesService(IHero player) {
        heroes = new ArrayList<>();
        this.player = player;
    }

    public IHero getPlayer() {
        return this.player;
    }

    private void removeDeadHeroes() {
        List<IHero> dead = new ArrayList<>();
        for (IHero hero : heroes) {
            if (hero.isDead()) {
                dead.add(hero);
            }
        }
        heroes.removeAll(dead);
    }

    public IHero getHero(int x, int y) {
        removeDeadHeroes();
        if (player.getX() == x && player.getY() == y) {
            return player;
        }
        for (IHero hero : heroes) {
            if (hero.getX() == x && hero.getY() == y) {
                return hero;
            }
        }
        return null;
    }

    @Override
    public void addHero(IHero newHero) {
        heroes.add(newHero);
    }

    public List<IHero> heroes() {
        removeDeadHeroes();
        List<IHero> outputList = new ArrayList<>();
        outputList.add(player);
        outputList.addAll(heroes);
        return outputList;
    }
}
