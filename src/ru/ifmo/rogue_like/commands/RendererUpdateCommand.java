package ru.ifmo.rogue_like.commands;

import ru.ifmo.rogue_like.rendering_system.IRenderer;

public class RendererUpdateCommand implements ICommand {
    private final IRenderer renderer;

    public RendererUpdateCommand(IRenderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void apply() {
        renderer.render();
    }
}
