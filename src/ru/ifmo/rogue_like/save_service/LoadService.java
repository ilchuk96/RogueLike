package ru.ifmo.rogue_like.save_service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.ifmo.rogue_like.map.IMap;
import ru.ifmo.rogue_like.map.Map;

public class LoadService {
    private final String filePath;

    public LoadService(String filePath) {
        this.filePath = filePath;
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
