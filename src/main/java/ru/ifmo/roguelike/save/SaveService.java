package ru.ifmo.roguelike.save;

import java.io.*;

import com.google.gson.Gson;
import ru.ifmo.roguelike.App;
import ru.ifmo.roguelike.Settings;

/**
 * Save and load game
 */
public class SaveService {
    private final String filePath = Settings.getProperty("save.file", String.class);

    public void save(App app) throws SaveException {
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(filePath))) {
            Gson gson = GsonConstructor.getGson();
            gson.toJson(new AppSerialization(app), writer);
        } catch (IOException e) {
            throw new SaveException(e);
        }
    }

    public App load() throws LoadException {
        try (Reader reader = new InputStreamReader(new FileInputStream(filePath))) {
            Gson gson = GsonConstructor.getGson();
            AppSerialization appSerialization = gson.fromJson(reader, AppSerialization.class);
            return appSerialization.getApp();
        } catch (IOException e) {
            throw new LoadException(e);
        }
    }
}
