package ru.ifmo.rogue_like.menu;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class MenuEntry {
    private String name;
    private Runnable handler;
    public MenuEntry(String name, Runnable handler) {
        this.name = name;
        this.handler = handler;
    }

    public String getName() {
        return name;
    }

    public void choose() {
        handler.run();
    }
}
