package ru.ifmo.rogue_like.rendering_system;

public interface IRenderable {
    IView getView(long time);
}
