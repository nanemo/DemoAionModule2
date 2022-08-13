package entity.organism.predators;

import abstractions.Animal;
import abstractions.Herbivore;
import abstractions.Predator;
import controller.Cell;
import controller.CellInitializer;
import controller.Coordinate;
import entity.organism.herbivores.Duck;
import entity.organism.herbivores.Mouse;
import entity.organism.herbivores.Rabbit;
import property.organismproperty.predatorproperty.EagleProperties;
import property.util.BornOrganism;
import property.util.EatableAnimal;
import property.util.MovableAnimal;

import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class Eagle extends Predator implements MovableAnimal, EatableAnimal, BornOrganism {

    public Eagle(double weight) {
        super(weight);
    }

    private CellInitializer cellInitializer = new CellInitializer();

    @Override
    public <T extends Animal> void move(Coordinate coordinate, T t) {
        Coordinate newCoordinates = defineNewDirection(coordinate, EagleProperties.STEP);
        if (eagleCountIsNotFull(newCoordinates)) {
            cellInitializer.moveAnimalToNewCoordinate(newCoordinates, coordinate, t);
        }
    }

    @Override
    public <T extends Animal> void eat(Coordinate coordinate, T t) {
        Cell currentCell = cellInitializer.island.getCells(coordinate);
        Iterator<Herbivore> iteratorForHerbivores = currentCell.getHerbivoreList().iterator();
        while (iteratorForHerbivores.hasNext() && t.getWeight() <= EagleProperties.MAX_WEIGHT_EAGLE) {
            if (iteratorForHerbivores.next() instanceof Rabbit && ThreadLocalRandom.current().nextInt(EagleProperties.CHANCE_TO_EAT_RABBIT) == 0) {
                eatRabbit(t);
                iteratorForHerbivores.remove();
            } else if (iteratorForHerbivores.next() instanceof Mouse && ThreadLocalRandom.current().nextInt(EagleProperties.CHANCE_TO_EAT_MOUSE) == 0) {
                eatMouse(t);
                iteratorForHerbivores.remove();
            } else if (iteratorForHerbivores.next() instanceof Duck && ThreadLocalRandom.current().nextInt(EagleProperties.CHANCE_TO_EAT_DUCK) == 0) {
                eatDuck(t);
                iteratorForHerbivores.remove();
            }
        }
        Iterator<Predator> iteratorForPredators = currentCell.getPredatorList().iterator();
        while (iteratorForPredators.hasNext() && t.getWeight() <= EagleProperties.MAX_WEIGHT_EAGLE) {
            if (iteratorForPredators.next() instanceof Fox && ThreadLocalRandom.current().nextInt(EagleProperties.CHANCE_TO_EAT_FOX) == 0) {
                eatFox(t);
                iteratorForPredators.remove();
            }
        }
    }

    @Override
    public void bornOrganism(Coordinate coordinate) {
        if (ThreadLocalRandom.current().nextBoolean() && eagleCountIsNotFull(coordinate)) {
            Animal newBreadedAnimal = new Eagle(EagleProperties.MIN_WEIGHT_EAGLE);
            cellInitializer.initializeBreadedAnimalToCell(coordinate, newBreadedAnimal);
        }
    }

    private boolean eagleCountIsNotFull(Coordinate coordinateForCount) {
        int eagleCount = 0;

        for (Predator eaglesInPredators : cellInitializer.island.getCells(coordinateForCount).getPredatorList()) {
            if (eaglesInPredators instanceof Eagle) {
                eagleCount++;
            }
        }

        return eagleCount < EagleProperties.MAX_COUNT_EAGLE;
    }

}
