package ru.ifmo.rogue_like.heroes.Magic;

import ru.ifmo.rogue_like.heroes.mobs.Hero;
import ru.ifmo.rogue_like.map.IMap;

import java.util.ArrayList;
import java.util.List;

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

    public void cast(IMap map, Hero hero) {
        magics.get(inUse).cast(map, hero);
    }

    public List<Magic> getMagics() {
        return magics;
    }

    public int getInUse() {
        return inUse;
    }

    public void setInUse(int inUse) {
        this.inUse = inUse;
    }
}
