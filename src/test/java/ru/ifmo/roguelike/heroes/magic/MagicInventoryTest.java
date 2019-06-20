package ru.ifmo.roguelike.heroes.magic;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MagicInventoryTest {

    @Test
    public void testMagicInventoryChangesInUse() {
        MagicInventory magicInventory = new MagicInventory();
        magicInventory.setInUse(1);
        assertEquals(1, magicInventory.getInUse());
    }
}
