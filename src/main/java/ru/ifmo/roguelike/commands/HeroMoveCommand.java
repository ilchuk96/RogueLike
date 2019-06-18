package ru.ifmo.roguelike.commands;

import java.util.ArrayList;
import java.util.List;

import ru.ifmo.roguelike.commands.generators.HeroCommandGenerator;
import ru.ifmo.roguelike.commands.generators.ICommandGenerator;
import ru.ifmo.roguelike.heroes.IHeroesService;
import ru.ifmo.roguelike.heroes.MoveAction;
import ru.ifmo.roguelike.heroes.mobs.IHero;
import ru.ifmo.roguelike.map.IMap;

public class HeroMoveCommand implements ICommand {
    private final IHero hero;
    private final MoveAction moveAction;
    private final IHeroesService heroesService;
    private final IMap map;

    public HeroMoveCommand(IHero hero, IMap map, IHeroesService heroesService, MoveAction moveAction) {
        this.hero = hero;
        this.map = map;
        this.heroesService = heroesService;
        this.moveAction = moveAction;
    }

    public List<ICommandGenerator> apply() {
        int newXCor = hero.getX() + moveAction.getX();
        int newYCor = hero.getY() + moveAction.getY();
        IHero newHero = map.updateMap(hero.getX(), hero.getY(), moveAction);
        List<ICommandGenerator> commandGenerators = new ArrayList<>();
        if (newHero != null) {
            heroesService.addHero(newHero);
            commandGenerators.add(new HeroCommandGenerator(newHero, map, heroesService));
        }
        if (map.isEmpty(newXCor, newYCor)) {
            hero.move(heroesService, moveAction);
        }
        return commandGenerators;
    }
}
