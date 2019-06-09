package ru.ifmo.rogue_like.save_service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.ifmo.rogue_like.map.IMap;

public class SaveService {
    private final String filePath;

    public SaveService(String filePath) {
        this.filePath = filePath;
    }

    public void save(IMap map) throws SaveException {
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(filePath))) {
            Gson gson = GsonConstructor.getGson();
            gson.toJson(map, writer);
        } catch (IOException e) {
            throw new SaveException(e);
        }
    }
}
