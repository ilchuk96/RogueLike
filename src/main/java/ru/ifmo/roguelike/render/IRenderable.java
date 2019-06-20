package ru.ifmo.roguelike.render;

/**
 * Objects that can be drawn
 */
public interface IRenderable {
    IView getView(long time);
}
