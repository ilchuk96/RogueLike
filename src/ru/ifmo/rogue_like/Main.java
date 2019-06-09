package ru.ifmo.rogue_like;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import ru.ifmo.rogue_like.heroes.player.PlayerListener;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        Properties properties = new Properties();
        properties.load(new InputStreamReader(new FileInputStream("config.properties")));
        Settings.addPropertyFile(properties);
        LoadMenu lm = new LoadMenu();
        while (lm.isVisible()) {
            Thread.sleep(100);
        }
        System.out.println(lm.getMap());
        PlayerListener playerListener = new PlayerListener(lm.getMap());
    }
}