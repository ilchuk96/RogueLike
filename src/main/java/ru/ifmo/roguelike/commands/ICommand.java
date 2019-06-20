package ru.ifmo.roguelike.commands;

import java.util.List;

import ru.ifmo.roguelike.commands.generators.ICommandGenerator;

public interface ICommand {
    List<ICommandGenerator> apply();
}
