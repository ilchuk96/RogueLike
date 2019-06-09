package ru.ifmo.rogue_like.commands;

import java.util.List;

import ru.ifmo.rogue_like.command_generators.ICommandGenerator;

public interface ICommand {
    List<ICommandGenerator> apply();
}
