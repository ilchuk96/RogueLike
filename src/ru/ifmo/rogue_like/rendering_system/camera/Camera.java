package ru.ifmo.rogue_like.rendering_system.camera;

import ru.ifmo.rogue_like.rendering_system.IRenderable;
import ru.ifmo.rogue_like.rendering_system.IView;

import java.util.ArrayList;
import java.util.List;

public class Camera implements ICamera {
    private int cameraPositionX, cameraPositionY;

    private List<IRenderable> renderableObjects = new ArrayList<>();
    private int sizeX, sizeY;
    private char[][] frame;


    public Camera(int sizeX, int sizeY, int cameraPositionX, int cameraPositionY) {
        this.cameraPositionX = 0;
        this.cameraPositionY = 0;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.frame = new char[sizeX][sizeY];
    }

    @Override
    public void addRenderableObject(IRenderable renderable) {
        renderableObjects.add(renderable);
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
                int positionY = y + cameraPositionY + view.getY();
                if (positionY < 0 || positionY >= sizeY) {
                    continue;
                }
                for (int x = 0; x < view.getView()[y].length; x++) {
                    int positionX = x + cameraPositionX + view.getX();
                    if (positionX < 0 || positionX >= sizeX) {
                        continue;
                    }
                    frame[positionY][positionX] = view.getView()[x][y];
                }
            }
        }
    }

    @Override
    public void setCameraPosition(int x, int y) {
        this.cameraPositionX = x;
        this.cameraPositionY = y;
    }

    @Override
    public void setCameraPositionX(int x) {
        this.cameraPositionX = x;
    }

    @Override
    public void setCameraPositionY(int y) {
        this.cameraPositionY = y;
    }

    @Override
    public char[][] getView() {
        return frame;
    }
}
