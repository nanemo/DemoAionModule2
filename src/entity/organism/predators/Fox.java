package entity.organism.predators;

import abstractions.Animal;
import abstractions.Herbivore;
import abstractions.Predator;
import controller.Cell;
import controller.CellInitializer;
import controller.Coordinate;
import entity.organism.herbivores.Caterpillar;
import entity.organism.herbivores.Duck;
import entity.organism.herbivores.Mouse;
import entity.organism.herbivores.Rabbit;
import property.organismproperty.predatorproperty.FoxProperties;
import property.util.BornOrganism;
import property.util.EatableAnimal;
import property.util.MovableAnimal;

import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class Fox extends Predator implements MovableAnimal, EatableAnimal, BornOrganism {

    public Fox(double weight) {
        super(weight);
    }

    private CellInitializer cellInitializer = new CellInitializer();

    @Override
    public <T extends Animal> void move(Coordinate coordinate, T t) {
        Coordinate newCoordinates = defineNewDirection(coordinate, FoxProperties.STEP);
        if (foxCountIsNotFull(newCoordinates)) {
            cellInitializer.moveAnimalToNewCoordinate(newCoordinates, coordinate, t);
        }
    }

    @Override
    public <T extends Animal> void eat(Coordinate coordinate, T t) {
        Cell currentCell = cellInitializer.island.getCells(coordinate);
        Iterator<Herbivore> iteratorForHerbivores = currentCell.getHerbivoreList().iterator();
        while (iteratorForHerbivores.hasNext() && t.getWeight() <= FoxProperties.MAX_WEIGHT_FOX) {
            if (iteratorForHerbivores.next() instanceof Rabbit && ThreadLocalRandom.current().nextInt(FoxProperties.CHANCE_TO_EAT_RABBIT) == 0) {
                eatRabbit(t);
                iteratorForHerbivores.remove();
            } else if (iteratorForHerbivores.next() instanceof Mouse && ThreadLocalRandom.current().nextInt(FoxProperties.CHANCE_TO_EAT_MOUSE) == 0) {
                eatMouse(t);
                iteratorForHerbivores.remove();
            } else if (iteratorForHerbivores.next() instanceof Duck && ThreadLocalRandom.current().nextInt(FoxProperties.CHANCE_TO_EAT_DUCK) == 0) {
                eatDuck(t);
                iteratorForHerbivores.remove();
            } else if (iteratorForHerbivores.next() instanceof Caterpillar && ThreadLocalRandom.current().nextInt(FoxProperties.CHANCE_TO_EAT_CATERPILLAR) == 0) {
                eatCaterpillar(t);
                iteratorForHerbivores.remove();
            }
        }
    }

    @Override
    public void bornOrganism(Coordinate coordinate) {
        if (ThreadLocalRandom.current().nextBoolean() && foxCountIsNotFull(coordinate)) {
            Animal newBreadedAnimal = new Fox(FoxProperties.MIN_WEIGHT_FOX);
            cellInitializer.initializeBreadedAnimalToCell(coordinate, newBreadedAnimal);
        }
    }

    private boolean foxCountIsNotFull(Coordinate coordinateForCount) {
        int foxCount = 0;

        for (Predator foxesInPredator : cellInitializer.island.getCells(coordinateForCount).getPredatorList()) {
            if (foxesInPredator instanceof Fox) {
                foxCount++;
            }
        }

        return foxCount < FoxProperties.MAX_COUNT_FOX;
    }

}
