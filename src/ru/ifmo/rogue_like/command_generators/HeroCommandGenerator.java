package ru.ifmo.rogue_like.command_generators;

import ru.ifmo.rogue_like.commands.HeroMoveCommand;
import ru.ifmo.rogue_like.commands.ICommand;
import ru.ifmo.rogue_like.heroes.IHeroesService;
import ru.ifmo.rogue_like.heroes.MoveAction;
import ru.ifmo.rogue_like.heroes.mobs.IHero;
import ru.ifmo.rogue_like.map.IMap;

public class HeroCommandGenerator implements ICommandGenerator {
    private IHero hero;
    private IMap map;
    private IHeroesService heroesService;

    public HeroCommandGenerator(IHero hero, IMap map, IHeroesService heroesService) {
        this.hero = hero;
        this.map = map;
        this.heroesService = heroesService;
    }

    @Override
    public ICommand getCommand() {
        MoveAction moveAction = hero.getMove(heroesService, map);
        return new HeroMoveCommand(hero, map, heroesService, moveAction);
    }
}
