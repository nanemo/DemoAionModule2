package entity.organism.predators;

import abstractions.Animal;
import abstractions.Herbivore;
import abstractions.Predator;
import controller.Cell;
import controller.CellInitializer;
import controller.Coordinate;
import entity.organism.herbivores.*;
import property.organismproperty.predatorproperty.WolfProperties;
import property.util.BornOrganism;
import property.util.EatableAnimal;
import property.util.MovableAnimal;

import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class Wolf extends Predator implements MovableAnimal, EatableAnimal, BornOrganism {

    public Wolf(double weight) {
        super(weight);
    }

    private CellInitializer cellInitializer = new CellInitializer();

    @Override
    public <T extends Animal> void move(Coordinate coordinate, T t) {
        Coordinate newCoordinates = defineNewDirection(coordinate, WolfProperties.STEP);
        if (wolfCountIsNotFull(newCoordinates)) {
            cellInitializer.moveAnimalToNewCoordinate(newCoordinates, coordinate, t);
        }
    }

    @Override
    public <T extends Animal> void eat(Coordinate coordinate, T t) {
        Cell currentCell = cellInitializer.island.getCells(coordinate);
        Iterator<Herbivore> iteratorForHerbivores = currentCell.getHerbivoreList().iterator();
        while (iteratorForHerbivores.hasNext() && t.getWeight() <= WolfProperties.MAX_WEIGHT_WOLF) {
            if (iteratorForHerbivores.next() instanceof Horse && ThreadLocalRandom.current().nextInt(WolfProperties.CHANCE_TO_EAT_HORSE) == 0) {
                eatHorse(t);
                iteratorForHerbivores.remove();
            } else if (iteratorForHerbivores.next() instanceof Deer && ThreadLocalRandom.current().nextInt(WolfProperties.CHANCE_TO_EAT_DEER) == 0) {
                eatDeer(t);
                iteratorForHerbivores.remove();
            } else if (iteratorForHerbivores.next() instanceof Rabbit && ThreadLocalRandom.current().nextInt(WolfProperties.CHANCE_TO_EAT_RABBIT) == 0) {
                eatRabbit(t);
                iteratorForHerbivores.remove();
            } else if (iteratorForHerbivores.next() instanceof Mouse && ThreadLocalRandom.current().nextInt(WolfProperties.CHANCE_TO_EAT_MOUSE) == 0) {
                eatMouse(t);
                iteratorForHerbivores.remove();
            } else if (iteratorForHerbivores.next() instanceof Goat && ThreadLocalRandom.current().nextInt(WolfProperties.CHANCE_TO_EAT_GOAT) == 0) {
                eatGoat(t);
                iteratorForHerbivores.remove();
            } else if (iteratorForHerbivores.next() instanceof Sheep && ThreadLocalRandom.current().nextInt(WolfProperties.CHANCE_TO_EAT_SHEEP) == 0) {
                eatSheep(t);
                iteratorForHerbivores.remove();
            } else if (iteratorForHerbivores.next() instanceof Boar && ThreadLocalRandom.current().nextInt(WolfProperties.CHANCE_TO_EAT_BOAR) == 0) {
                eatBoar(t);
                iteratorForHerbivores.remove();
            } else if (iteratorForHerbivores.next() instanceof Buffalo && ThreadLocalRandom.current().nextInt(WolfProperties.CHANCE_TO_EAT_BUFFALO) == 0) {
                eatBuffalo(t);
                iteratorForHerbivores.remove();
            } else if (iteratorForHerbivores.next() instanceof Duck && ThreadLocalRandom.current().nextInt(WolfProperties.CHANCE_TO_EAT_DUCK) == 0) {
                eatDuck(t);
                iteratorForHerbivores.remove();
            }
        }
    }

    @Override
    public void bornOrganism(Coordinate coordinate) {
        if (ThreadLocalRandom.current().nextBoolean() && wolfCountIsNotFull(coordinate)) {
            Animal newBreadedAnimal = new Wolf(WolfProperties.MIN_WEIGHT_WOLF);
            cellInitializer.initializeBreadedAnimalToCell(coordinate, newBreadedAnimal);
        }
    }

    private boolean wolfCountIsNotFull(Coordinate coordinateForCount) {
        int wolfCount = 0;

        for (Predator wolfsInPredators : cellInitializer.island.getCells(coordinateForCount).getPredatorList()) {
            if (wolfsInPredators instanceof Wolf) {
                wolfCount++;
            }
        }

        return wolfCount < WolfProperties.MAX_COUNT_WOLF;
    }

}
