package ru.ifmo.rogue_like.map;

import ru.ifmo.rogue_like.map.squares.Floor;
import ru.ifmo.rogue_like.map.squares.Wall;
import ru.ifmo.rogue_like.rendering_system.IRenderable;
import ru.ifmo.rogue_like.rendering_system.IView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomMap implements IMap {
    private List<List<ISquare>> field;
    private Random random;

    /***
     * maxX and maxY must be divisible by 8
     */
    public RandomMap() {
        random = new Random();
        int maxX = 1024;
        int maxY = 1024;

        field = new ArrayList<>();
        for (int i = 0; i < maxX; i++) {
            List<ISquare> lst = new ArrayList<>();
            for (int j = 0; j < maxY; j++) {
                lst.add(null);
            }
            field.add(lst);
        }

        int curX = maxX / 2;
        int curY = maxY / 2;

        field.get(curX).set(curY, new Floor());

        for (int i = 1; i <= 3; i++) {
            for (int j = -4; j <= 3; j++) {
                (field.get(curX + i)).set(curY + j, new Floor());
            }
        }
        for (int j = -4; j <= 3; j++) {
            (field.get(curX)).set(curY + j, new Wall());
        }
    }


    private Tile genTile() {
        Tile tile = new Tile();
        int rnd = random.nextInt(3);
        if (rnd == 0) {
            rnd = random.nextInt(3);
            if (rnd == 0) {
                tile.setSquare(3, 0, new Wall());
                tile.setSquare(3, 3, new Wall());
                tile.setSquare(2, 3, new Wall());
                tile.setSquare(1, 3, new Wall());
                tile.setSquare(0, 3, new Wall());
                tile.setSquare(0, 2, new Wall());
                tile.setSquare(0, 1, new Wall());
                tile.setSquare(0, 0, new Wall());
            } else if (rnd == 1) {
                tile.setSquare(3, 0, new Wall());
                tile.setSquare(3, 3, new Wall());
                tile.setSquare(2, 0, new Wall());
                tile.setSquare(1, 0, new Wall());
                tile.setSquare(0, 3, new Wall());
                tile.setSquare(0, 2, new Wall());
                tile.setSquare(0, 1, new Wall());
                tile.setSquare(0, 0, new Wall());
            } else {
                tile.setSquare(3, 0, new Wall());
                tile.setSquare(3, 3, new Wall());
                tile.setSquare(2, 3, new Wall());
                tile.setSquare(1, 3, new Wall());
                tile.setSquare(0, 3, new Wall());
                tile.setSquare(1, 0, new Wall());
                tile.setSquare(2, 0, new Wall());
                tile.setSquare(0, 0, new Wall());
            }
        } else {
            if (rnd == 1) {
                rnd = random.nextInt(3);
                if (rnd == 0) {
                    tile.setSquare(0, 0, new Wall());
                    tile.setSquare(0, 1, new Wall());
                    tile.setSquare(0, 2, new Wall());
                    tile.setSquare(0, 3, new Wall());
                } else if (rnd == 1) {
                    tile.setSquare(0, 0, new Wall());
                    tile.setSquare(1, 0, new Wall());
                    tile.setSquare(2, 0, new Wall());
                    tile.setSquare(3, 0, new Wall());
                } else {
                    tile.setSquare(3, 0, new Wall());
                    tile.setSquare(3, 1, new Wall());
                    tile.setSquare(3, 2, new Wall());
                    tile.setSquare(3, 3, new Wall());
                }
            }
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    rnd = random.nextInt(4);
                    if (rnd == 0) {
                        tile.setSquare(i * 3, j * 3, new Wall());
                    }
                }
            }
        }
        return tile;
    }

    private void rotateTile(Tile tile, char direction) {
        if (direction == 'a') {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < i; j++) {
                    ISquare square = tile.getSquare(i, j);
                    tile.setSquare(i, j, tile.getSquare(j, i));
                    tile.setSquare(j, i, square);
                }
            }
        }
        if (direction == 'd') {
            rotateTile(tile, 's');
            rotateTile(tile, 'a');
        }
        if (direction == 's') {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 2; j++) {
                    ISquare square = tile.getSquare(i, j);
                    tile.setSquare(i, j, tile.getSquare(i, 3 - j));
                    tile.setSquare(i, 3 - j, square);
                }
            }
        }

    }

    private Tile getNewTile(char direction) {
        Tile tile = genTile();
        rotateTile(tile, direction);
        return tile;
    }

    @Override
    public List<List<ISquare>> getField() {
        return field;
    }

    @Override
    public void updateMap(int x, int y, char direction) {
        if (direction == 'w' && field.get(x - 1).get(y) == null) {
            Tile tile = getNewTile(direction);
            int corner = (y / 4) * 4;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    field.get(x - 4 + i).set(corner + j, tile.getSquare(i, j));
                }
            }
        }
        if (direction == 'a' && field.get(x).get(y - 1) == null) {
            Tile tile = getNewTile(direction);
            int corner = (x / 4) * 4;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    field.get(corner + i).set(y - 4 + j, tile.getSquare(i, j));
                }
            }
        }
        if (direction == 's' && field.get(x + 1).get(y) == null) {
            Tile tile = getNewTile(direction);
            int corner = (y / 4) * 4;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    field.get(x + 1 + i).set(corner + j, tile.getSquare(i, j));
                }
            }
        }
        if (direction == 'd' && field.get(x + 1).get(y) == null) {
            Tile tile = getNewTile(direction);
            int corner = (x / 4) * 4;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    field.get(corner + i).set(y + 1 + j, tile.getSquare(i, j));
                }
            }
        }
    }

    @Override
    public IView getView(long time) {
        char[][] view = new char[field.get(0).size()][field.size()];
        int i = 0, j = 0;
        for (List<ISquare> t : field) {
            for (ISquare square : t) {
                if (square instanceof Floor) {
                    view[i][j] = '.';
                } else {
                    view[i][j] = '#';
                }
                if (square == null) {
                    view[i][j] = '*';
                }
                j++;
            }
            j = 0;
            i++;
        }
        return new IView() {
            @Override
            public int getX() {
                return 0;
            }

            @Override
            public int getY() {
                return 0;
            }

            @Override
            public char[][] getView() {
                return view;
            }
        };
    }
}
