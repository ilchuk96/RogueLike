package ru.ifmo.roguelike.render.camera;

import ru.ifmo.roguelike.render.IRenderable;

public interface ICamera {
    void addRenderableObject(IRenderable renderable);

    void removeRenderableObject(IRenderable renderable);

    char[][] getView();

    void update(long time);

    void setCameraPosition(int x, int y);

    void setCameraPositionX(int x);

    void setCameraPositionY(int y);
}
