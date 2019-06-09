package ru.ifmo.rogue_like.heroes.magic;

import java.util.ArrayList;
import java.util.List;

import ru.ifmo.rogue_like.heroes.IHeroesService;
import ru.ifmo.rogue_like.heroes.mobs.Hero;

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
}
