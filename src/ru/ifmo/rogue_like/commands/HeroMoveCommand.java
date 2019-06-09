package ru.ifmo.rogue_like.commands;

import ru.ifmo.rogue_like.heroes.IHeroesService;
import ru.ifmo.rogue_like.heroes.MoveAction;
import ru.ifmo.rogue_like.heroes.mobs.IHero;
import ru.ifmo.rogue_like.map.IMap;

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

    public void apply() {
        int newXCor = hero.getX() + moveAction.getX();
        int newYCor = hero.getY() + moveAction.getY();
        IHero newHero = map.updateMap(hero.getX(), hero.getY(), moveAction);
        if (newHero != null) {
            heroesService.addHero(newHero);
        }
        if (map.isEmpty(newXCor, newYCor)) {
            IHero heroOnTheWay = heroesService.getHero(newXCor, newYCor);
            if (heroOnTheWay == null) {
                hero.move(heroesService, moveAction);
            }
        }
    }
}
