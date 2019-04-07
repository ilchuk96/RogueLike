package src.ru.ifmo.rogue_like.renderer;

import java.util.ArrayList;
import java.util.List;

public class Renderer implements IRenderer {
    List<IRenderable> renderableObjects = new ArrayList<>();
    int sizeX, sizeY;

    char[][] frame;

    public Renderer(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        frame = new char[sizeX][sizeY];
    }

    void addRenderableObject(IRenderable renderable) {
        renderableObjects.add(renderable);
    }

    private void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        for (int i = 0; i < frame.length; i++) {
            for (int g = 0; g < frame[i].length; g++) {
                frame[i][g] = '.';
            }
        }
    }

    @Override
    public void render() {
        for (IRenderable renderable : renderableObjects) {
            IView view = renderable.getView();
            for (int y = 0; y < view.getView().length; y++) {
                for (int x = 0; x < view.getView()[y].length; x++) {
                    frame[y + view.getY()][x + view.getX()] = view.getView()[x][y];
                }
            }
        }
        for (char[] row : frame) {
            for (char elem : row) {
                System.out.print(elem);
            }
            System.out.println();
        }
    }
}
