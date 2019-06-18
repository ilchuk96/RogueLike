package ru.ifmo.roguelike.heroes.magic;

import java.util.ArrayList;
import java.util.List;

import ru.ifmo.roguelike.heroes.IHeroesService;
import ru.ifmo.roguelike.heroes.mobs.Hero;

public class MagicInventory {
    private List<Magic> magics;
    private int inUse;


    public MagicInventory() {
        magics = new ArrayList<>();
        magics.add(new Confuse());
        magics.add(new Heal());
        magics.add(new Fire());
        magics.add(new Scare());
        inUse = 0;
    }

    public void cast(IHeroesService heroesService, Hero hero) {
        magics.get(inUse).cast(heroesService, hero);
    }

    public List<Magic> getMagics() {
        return magics;
    }

    public int getInUse() {
        return inUse;
    }

    public void setInUse(int toUse) {
        inUse = toUse;
    }
}
