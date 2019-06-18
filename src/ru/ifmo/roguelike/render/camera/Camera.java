package ru.ifmo.roguelike.render.camera;

import java.util.ArrayList;
import java.util.List;

import ru.ifmo.roguelike.render.IRenderable;
import ru.ifmo.roguelike.heroes.mobs.IHero;
import ru.ifmo.roguelike.map.IPositionable;
import ru.ifmo.roguelike.render.IView;

public class Camera implements ICamera {
    private int cameraPositionX, cameraPositionY;

    private List<IRenderable> renderableObjects = new ArrayList<>();
    private int sizeX, sizeY;
    private char[][] frame;
    private IPositionable position;

    public Camera(int sizeX, int sizeY, IPositionable cameraPosition) {
        this.position = cameraPosition;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.frame = new char[sizeX][sizeY];
    }

    @Override
    public void addRenderableObject(IRenderable renderable) {
        if (renderableObjects.contains(renderable)) {
            return;
        }
        renderableObjects.add(renderable);
    }

    @Override
    public void removeRenderableObject(IRenderable renderable) {
        if (!renderableObjects.contains(renderable)) {
            return;
        }
        renderableObjects.remove(renderable);
    }

    @Override
    public void update(long time) {
        setCameraPosition(position.getX(), position.getY());
        for (int i = 0; i < frame.length; i++) {
            for (int g = 0; g < frame[i].length; g++) {
                frame[i][g] = '.';
            }
        }
        List<IRenderable> toDelete = new ArrayList<>();
        for (IRenderable renderable : renderableObjects) {
            if (renderable instanceof IHero && ((IHero) renderable).isDead()) {
                toDelete.add(renderable);
                continue;
            }
            IView view = renderable.getView(time);
            for (int y = 0; y < view.getView().length; y++) {
                int positionY = y - cameraPositionY + view.getY();
                if (positionY < 0 || positionY >= sizeY) {
                    continue;
                }
                for (int x = 0; x < view.getView()[y].length; x++) {
                    int positionX = x - cameraPositionX + view.getX();
                    if (positionX < 0 || positionX >= sizeX) {
                        continue;
                    }
                    frame[positionY][positionX] = view.getView()[x][y];
                }
            }
        }
        renderableObjects.removeAll(toDelete);
    }

    @Override
    public void setCameraPosition(int x, int y) {
        this.cameraPositionX = x - this.sizeX / 2;
        this.cameraPositionY = y - this.sizeY / 2;
    }

    @Override
    public void setCameraPositionX(int x) {
        this.cameraPositionX = x - this.sizeX / 2;
    }

    @Override
    public void setCameraPositionY(int y) {
        this.cameraPositionY = y - this.sizeY / 2;
    }

    @Override
    public char[][] getView() {
        return frame;
    }
}
