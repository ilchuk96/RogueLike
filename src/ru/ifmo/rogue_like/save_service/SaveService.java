package ru.ifmo.rogue_like.save_service;

import java.io.*;

import com.google.gson.Gson;
import ru.ifmo.rogue_like.Settings;
import ru.ifmo.rogue_like.map.IMap;
import ru.ifmo.rogue_like.map.Map;

public class SaveService {
    private final String filePath = Settings.getProperty("save.file", String.class);

    public void save(IMap map) throws SaveException {
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(filePath))) {
            Gson gson = GsonConstructor.getGson();
            gson.toJson(map, writer);
        } catch (IOException e) {
            throw new SaveException(e);
        }
    }

    public IMap load() throws LoadException {
        IMap map;
        try (Reader reader = new InputStreamReader(new FileInputStream(filePath))) {
            Gson gson = GsonConstructor.getGson();
            map = gson.fromJson(reader, Map.class);
        } catch (IOException e) {
            throw new LoadException(e);
        }
        return map;
    }
}
