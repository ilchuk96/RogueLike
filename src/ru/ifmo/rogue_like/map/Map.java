package ru.ifmo.rogue_like.map;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ru.ifmo.rogue_like.heroes.mobs.Hero;
import ru.ifmo.rogue_like.heroes.mobs.move_strategies.Agressive;
import ru.ifmo.rogue_like.heroes.mobs.move_strategies.Dilative;
import ru.ifmo.rogue_like.heroes.mobs.move_strategies.IHeroStrategy;
import ru.ifmo.rogue_like.heroes.mobs.move_strategies.Passive;
import ru.ifmo.rogue_like.map.squares.Floor;
import ru.ifmo.rogue_like.map.squares.Wall;
import ru.ifmo.rogue_like.rendering_system.IView;

public class Map implements IMap {
    private List<List<ISquare>> field;
    private Random random;
    private int maxX;
    private int maxY;
    private List<Hero> heroes;
    private List<Hero> newHeroes;
    private int heroX;
    private int heroY;

    /***
     * maxX and maxY must be divisible by 8
     */
    public Map(int x, int y) {
        random = new Random();
        maxX = x;
        maxY = y;
        heroes = new ArrayList<>();
        newHeroes = new ArrayList<>();
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
        heroX = curX + 1;
        heroY = curY;
    }

    public Map(int x, int y, String filepath) throws IOException {
        random = new Random();
        maxX = x;
        maxY = y;
        heroes = new ArrayList<>();
        newHeroes = new ArrayList<>();
        field = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filepath));
        String line;
        for (int i = 0; i < maxX; i++) {
            line = br.readLine();
            List<ISquare> lst = new ArrayList<>();
            for (int j = 0; j < maxY; j++) {
                char ch = line.charAt(j);
                if (ch == '+') {
                    lst.add(new Floor());
                } else if (ch == '=') {
                    lst.add(new Wall());
                } else if (ch == '$') {
                    heroX = i;
                    heroY = j;
                } else {
                    lst.add(null);
                }
            }
            field.add(lst);
        }
    }

    @Override
    public int getHeroX() {
        return heroX;
    }

    @Override
    public int getHeroY() {
        return heroY;
    }

    @Override
    public void addPlayer(Hero player) {
        field.get(player.getX()).set(player.getY(), player);
        heroes.add(player);
    }

    @Override
    public void deleteMob(int x, int y) {
        field.get(x).set(y, new Floor());
    }

    @Override
    public List<Hero> getHeroes() {
        heroes.addAll(newHeroes);
        newHeroes = new ArrayList<>();
        List<Hero> toRemove = new ArrayList<>();
        for (Hero hero : heroes) {
            if (hero.isDead()) {
                toRemove.add(hero);
                deleteMob(hero.getX(), hero.getY());
            }
        }
        heroes.removeAll(toRemove);
        return heroes;
    }

    @Override
    public void move(int x, int y, int dx, int dy) {
        ISquare t = field.get(x).get(y);
        field.get(x).set(y, field.get(x + dx).get(y + dy));
        field.get(x + dx).set(y + dy, t);
    }

    /*
    Generated for going left case, so (3,1) and (3,2) are always Floor().
     */
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
                    tile.setSquare(0, 3, new Wall());
                    tile.setSquare(1, 3, new Wall());
                    tile.setSquare(2, 3, new Wall());
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
        if (direction == 'w') {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < i; j++) {
                    ISquare square = tile.getSquare(i, j);
                    tile.setSquare(i, j, tile.getSquare(j, i));
                    tile.setSquare(j, i, square);
                }
            }
        }
        if (direction == 's') {
            rotateTile(tile, 'd');
            rotateTile(tile, 'w');
        }
        if (direction == 'd') {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 4; j++) {
                    ISquare square = tile.getSquare(i, j);
                    tile.setSquare(i, j, tile.getSquare(3 - i, j));
                    tile.setSquare(3 - i, j, square);
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
    public Hero updateMap(int x, int y, char direction) {
        Integer mobX = null;
        Integer mobY = null;

        if (direction == 'w' && y - 1 > 0 && field.get(x).get(y - 1) == null) {
            Tile tile = getNewTile(direction);
            int corner = (x / 4) * 4;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    field.get(corner + i).set(y - 4 + j, tile.getSquare(i, j));
                }
            }
            mobX = corner + 2;
            mobY = y - 2;
        }
        if (direction == 'a' && x - 1 > 0 && field.get(x - 1).get(y) == null) {
            Tile tile = getNewTile(direction);
            int corner = (y / 4) * 4;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    field.get(x - 4 + i).set(corner + j, tile.getSquare(i, j));
                }
            }
            mobX = x - 2;
            mobY = corner + 2;
        }
        if (direction == 's' && y + 1 < maxY && field.get(x).get(y + 1) == null) {
            Tile tile = getNewTile(direction);
            int corner = (x / 4) * 4;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    field.get(corner + i).set(y + 1 + j, tile.getSquare(i, j));
                }
            }
            mobX = corner + 2;
            mobY = y + 2;
        }
        if (direction == 'd' && x + 1 < maxX && field.get(x + 1).get(y) == null) {
            Tile tile = getNewTile(direction);
            int corner = (y / 4) * 4;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    field.get(x + 1 + i).set(corner + j, tile.getSquare(i, j));
                }
            }
            mobX = x + 2;
            mobY = corner + 2;
        }

        if (mobX != null) {
            IHeroStrategy strategy;
            int type = random.nextInt(3);
            if (type == 0) {
                strategy = new Dilative();
            } else if (type == 1) {
                strategy = new Passive();
            } else {
                strategy = new Agressive();
            }
            Hero mob = new Hero(strategy, mobX, mobY);
            field.get(mobX).set(mobY, mob);
            newHeroes.add(mob);
            return mob;
        }
        return null;
    }

    @Override
    public void saveMap(String filepath) throws IOException {
        FileWriter fileWriter = new FileWriter(new File(filepath));
        for (List<ISquare> lst : field) {
            StringBuilder sb = new StringBuilder();
            for (ISquare square : lst) {
                if (square instanceof Floor) {
                    sb.append("+");
                } else if (square instanceof Wall) {
                    sb.append("=");
                } else {
                    sb.append(".");
                }
            }
            fileWriter.write(sb.toString());
            fileWriter.write("\n");
        }
        fileWriter.close();
    }

    @Override
    public IView getView(long time) {
        char[][] view = new char[field.size()][field.get(0).size()];
        int i = 0, j = 0;
        for (List<ISquare> t : field) {
            for (ISquare square : t) {
                if (square instanceof Floor) {
                    view[i][j] = '+';
                }  else if (square instanceof Hero) {
                    view[i][j] = ((Hero) square).getView(0).getView()[0][0];
                } else {
                    view[i][j] = '=';
                }
                if (square == null) {
                    view[i][j] = '-';
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
