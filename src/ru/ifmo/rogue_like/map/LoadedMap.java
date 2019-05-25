package ru.ifmo.rogue_like.map;

import ru.ifmo.rogue_like.heroes.mobs.Hero;
import ru.ifmo.rogue_like.map.squares.Floor;
import ru.ifmo.rogue_like.map.squares.Wall;
import ru.ifmo.rogue_like.rendering_system.IView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadedMap implements IMap {
    private List<List<ISquare>> field;

    LoadedMap(String filepath) {
        field = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<ISquare> lst = new ArrayList<>();
                for (char ch : line.toCharArray()) {
                    if (ch == 'f') {
                        lst.add(new Floor());
                    }
                    if (ch == 'w') {
                        lst.add(new Wall());
                    }
                }
                field.add(lst);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<List<ISquare>> getField() {
        return field;
    }

    @Override
    public Hero updateMap(int x, int y, char direction) {
        return null;
    }

    @Override
    public IView getView(long time) {
        throw new UnsupportedOperationException();
    }
}
