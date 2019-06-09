package ru.ifmo.rogue_like.command_generators;

import ru.ifmo.rogue_like.commands.ICommand;

public interface ICommandGenerator {
    ICommand getCommand();
}
