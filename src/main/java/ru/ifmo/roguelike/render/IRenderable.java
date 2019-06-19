package ru.ifmo.roguelike.render;

public interface IRenderable {
    IView getView(long time);
}
