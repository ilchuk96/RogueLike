package ru.ifmo.roguelike.heroes;

/**
 * Move of a hero
 * x - difference on x direction
 * y - difference on y direction
 * type - 0 if move or do nothing, 1 if cast magic
 */
public class MoveAction {
    private int x;
    private int y;
    private int type;

    public MoveAction(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type =type;
        if (x != 0 || y != 0) {
            this.type = 0;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getType() {
        return type;
    }
}
