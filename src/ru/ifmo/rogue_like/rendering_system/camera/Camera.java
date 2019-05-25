package ru.ifmo.rogue_like.rendering_system.camera;

import java.util.ArrayList;
import java.util.List;

import ru.ifmo.rogue_like.heroes.mobs.Hero;
import ru.ifmo.rogue_like.map.IPositionable;
import ru.ifmo.rogue_like.rendering_system.IRenderable;
import ru.ifmo.rogue_like.rendering_system.IView;

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
        renderableObjects.add(renderable);
    }

    @Override
    public void removeRenderableObject(IRenderable renderable) {
        renderableObjects.remove(renderable);
    }

    @Override
    public void update(long time) {
        for (int i = 0; i < frame.length; i++) {
            for (int g = 0; g < frame[i].length; g++) {
                frame[i][g] = '.';
            }
        }
        for (IRenderable renderable : renderableObjects) {
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
        setCameraPosition(position.getX(), position.getY());
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
