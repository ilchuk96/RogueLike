package ru.ifmo.rogue_like;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        LoadMenu lm = new LoadMenu();
        while (lm.isVisible()) {
            Thread.sleep(100);
        }
        System.out.println(lm.getMap());
        App app = new App(lm.getMap());
        app.start();
    }
}