package ru.ifmo.rogue_like.rendering_system.camera;

import ru.ifmo.rogue_like.map.IMap;

public interface ICamera {
    void addRenderableObject(IMap renderable);

    char[][] getView();

    void update(long time);

    void setCameraPosition(int x, int y);

    void setCameraPositionX(int x);
    void setCameraPositionY(int y);
}
