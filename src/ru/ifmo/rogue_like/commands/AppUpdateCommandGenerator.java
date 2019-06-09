package ru.ifmo.rogue_like.commands;

import ru.ifmo.rogue_like.App;
import ru.ifmo.rogue_like.command_generators.ICommandGenerator;

public class AppUpdateCommandGenerator implements ICommandGenerator {
    private App app;

    public AppUpdateCommandGenerator(App app) {
        this.app = app;
    }

    @Override
    public ICommand getCommand() {
        return () -> this.app.update();
    }
}
