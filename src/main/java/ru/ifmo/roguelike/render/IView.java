package ru.ifmo.roguelike.render;

/**
 * a view to be drawn
 */
public interface IView {
    int getX();

    int getY();

    char[][] getView();
}
