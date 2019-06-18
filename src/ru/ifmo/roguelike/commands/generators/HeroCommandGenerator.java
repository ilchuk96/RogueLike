package ru.ifmo.roguelike.commands.generators;

import ru.ifmo.roguelike.commands.HeroMoveCommand;
import ru.ifmo.roguelike.commands.ICommand;
import ru.ifmo.roguelike.heroes.IHeroesService;
import ru.ifmo.roguelike.heroes.MoveAction;
import ru.ifmo.roguelike.heroes.mobs.IHero;
import ru.ifmo.roguelike.map.IMap;

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
        MoveAction moveAction;
        if (hero.isDead()) {
            moveAction = new MoveAction(0, 0, 0);
        } else {
            moveAction = hero.getMove(heroesService, map);
        }
        return new HeroMoveCommand(hero, map, heroesService, moveAction);
    }

    @Override
    public boolean isReady() {
        return true;
    }
}
