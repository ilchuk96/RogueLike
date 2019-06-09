package ru.ifmo.rogue_like.command_generators;

import ru.ifmo.rogue_like.commands.ICommand;
import ru.ifmo.rogue_like.commands.RendererUpdateCommand;
import ru.ifmo.rogue_like.rendering_system.IRenderer;

public class RendererCommandGenerator implements ICommandGenerator {
    private IRenderer renderer;

    public RendererCommandGenerator(IRenderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public ICommand getCommand() {
        return new RendererUpdateCommand(renderer);
    }
}
