package entity.organism.predators;

import abstractions.Animal;
import abstractions.Herbivore;
import abstractions.Predator;
import controller.Cell;
import controller.CellInitializer;
import controller.Coordinate;
import entity.organism.herbivores.*;
import property.organismproperty.predatorproperty.BearProperties;
import property.util.BornOrganism;
import property.util.EatableAnimal;
import property.util.MovableAnimal;

import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class Bear extends Predator implements MovableAnimal, EatableAnimal, BornOrganism {

    public Bear(Double weight) {
        super(weight);
    }

    private CellInitializer cellInitializer = new CellInitializer();

    @Override
    public <T extends Animal> void move(Coordinate coordinate, T t) {
        Coordinate newCoordinates = defineNewDirection(coordinate, BearProperties.STEP);
        if (bearCountIsNotFull(newCoordinates)) {
            cellInitializer.moveAnimalToNewCoordinate(newCoordinates, coordinate, t);
        }
    }

    @Override
    public <T extends Animal> void eat(Coordinate coordinate, T t) {
        Cell currentCell = cellInitializer.island.getCells(coordinate);
        Iterator<Herbivore> iteratorForHerbivores = currentCell.getHerbivoreList().iterator();
        while (iteratorForHerbivores.hasNext() && t.getWeight() <= BearProperties.MAX_WEIGHT_BEAR) {
            if (iteratorForHerbivores.next() instanceof Horse && ThreadLocalRandom.current().nextInt(BearProperties.CHANCE_TO_EAT_HORSE) == 0) {
                eatHorse(t);
                iteratorForHerbivores.remove();
            } else if (iteratorForHerbivores.next() instanceof Deer && ThreadLocalRandom.current().nextInt(BearProperties.CHANCE_TO_EAT_DEER) == 0) {
                eatDeer(t);
                iteratorForHerbivores.remove();
            } else if (iteratorForHerbivores.next() instanceof Rabbit && ThreadLocalRandom.current().nextInt(BearProperties.CHANCE_TO_EAT_RABBIT) == 0) {
                eatRabbit(t);
                iteratorForHerbivores.remove();
            } else if (iteratorForHerbivores.next() instanceof Mouse && ThreadLocalRandom.current().nextInt(BearProperties.CHANCE_TO_EAT_MOUSE) == 0) {
                eatMouse(t);
                iteratorForHerbivores.remove();
            } else if (iteratorForHerbivores.next() instanceof Goat && ThreadLocalRandom.current().nextInt(BearProperties.CHANCE_TO_EAT_GOAT) == 0) {
                eatGoat(t);
                iteratorForHerbivores.remove();
            } else if (iteratorForHerbivores.next() instanceof Sheep && ThreadLocalRandom.current().nextInt(BearProperties.CHANCE_TO_EAT_SHEEP) == 0) {
                eatSheep(t);
                iteratorForHerbivores.remove();
            } else if (iteratorForHerbivores.next() instanceof Boar && ThreadLocalRandom.current().nextInt(BearProperties.CHANCE_TO_EAT_BOAR) == 0) {
                eatBoar(t);
                iteratorForHerbivores.remove();
            } else if (iteratorForHerbivores.next() instanceof Buffalo && ThreadLocalRandom.current().nextInt(BearProperties.CHANCE_TO_EAT_BUFFALO) == 0) {
                eatBuffalo(t);
                iteratorForHerbivores.remove();
            } else if (iteratorForHerbivores.next() instanceof Duck && ThreadLocalRandom.current().nextInt(BearProperties.CHANCE_TO_EAT_DUCK) == 0) {
                eatDuck(t);
                iteratorForHerbivores.remove();
            }
        }
        Iterator<Predator> iteratorForPredators = currentCell.getPredatorList().iterator();
        while (iteratorForPredators.hasNext() && t.getWeight() <= BearProperties.MAX_WEIGHT_BEAR) {
            if (iteratorForPredators.next() instanceof Boa && ThreadLocalRandom.current().nextInt(BearProperties.CHANCE_TO_EAT_BOA) == 0) {
                eatBoa(t);
                iteratorForPredators.remove();
            }
        }
    }

    @Override
    public void bornOrganism(Coordinate coordinate) {
        if (ThreadLocalRandom.current().nextBoolean() && bearCountIsNotFull(coordinate)) {
            Animal newBreadedAnimal = new Bear(BearProperties.MIN_WEIGHT_BEAR);
            cellInitializer.initializeBreadedAnimalToCell(coordinate, newBreadedAnimal);
        }
    }

    private boolean bearCountIsNotFull(Coordinate coordinateForCount) {
        int bearCount = 0;

        for (Predator bearsInPredator : cellInitializer.island.getCells(coordinateForCount).getPredatorList()) {
            if (bearsInPredator instanceof Bear) {
                bearCount++;
            }
        }

        return bearCount < BearProperties.MAX_COUNT_BEAR;
    }

}
