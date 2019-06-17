package ru.ifmo.roguelike.commands.generators;

import ru.ifmo.roguelike.commands.ICommand;

public interface ICommandGenerator {
    ICommand getCommand();
}
