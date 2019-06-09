package ru.ifmo.rogue_like.heroes;

import java.util.ArrayList;
import java.util.List;

import ru.ifmo.rogue_like.heroes.mobs.IHero;

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

    public IHero getHero(int x, int y) {
        if (player.getX() == x && player.getY() == y) {
            return player;
        }
        for (IHero hero : heroes) {
            if (hero.getX() == x && hero.getY() == y) {
                return player;
            }
        }
        return null;
    }

    @Override
    public void addHero(IHero newHero) {
        heroes.add(newHero);
    }

    public List<IHero> heroes() {
        List<IHero> outputList = new ArrayList<>();
        outputList.add(player);
        outputList.addAll(heroes);
        return outputList;
    }
}
