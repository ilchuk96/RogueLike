package ru.ifmo.rogue_like.commands;

import java.util.ArrayList;
import java.util.List;

import ru.ifmo.rogue_like.command_generators.HeroCommandGenerator;
import ru.ifmo.rogue_like.command_generators.ICommandGenerator;
import ru.ifmo.rogue_like.commands.ICommand;
import ru.ifmo.rogue_like.heroes.IHeroesService;
import ru.ifmo.rogue_like.heroes.MoveAction;
import ru.ifmo.rogue_like.heroes.mobs.IHero;
import ru.ifmo.rogue_like.map.IMap;

public class PlayerMoveCommand implements ICommand {
    private final IHero hero;
    private final MoveAction moveAction;
    private final IHeroesService heroesService;
    private final IMap map;

    public PlayerMoveCommand(IHero hero, IMap map, IHeroesService heroesService, MoveAction moveAction) {
        this.hero = hero;
        this.map = map;
        this.heroesService = heroesService;
        this.moveAction = moveAction;
    }


    @Override
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
            IHero heroOnTheWay = heroesService.getHero(newXCor, newYCor);
            if (heroOnTheWay == null) {
                hero.move(heroesService, moveAction);
            }
        }
        return commandGenerators;
    }
}
