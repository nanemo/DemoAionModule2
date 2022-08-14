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
import property.organismproperty.predatorproperty.BoaProperties;
import property.util.BornOrganism;
import property.util.EatableAnimal;
import property.util.MovableAnimal;

import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Boa extends Predator implements MovableAnimal, EatableAnimal, BornOrganism {

    public Boa(double weight) {
        super(weight);
    }

    private CellInitializer cellInitializer = new CellInitializer();

    @Override
    public <T extends Animal> void move(Coordinate coordinate, T t) {
        Coordinate newCoordinates = defineNewDirection(coordinate, BoaProperties.STEP);
        if (boaCountIsNotFull(newCoordinates)) {
            cellInitializer.moveAnimalToNewCoordinate(newCoordinates, coordinate, t);
        }
    }

    @Override
    public <T extends Animal> void eat(Coordinate coordinate, T t) {
        Cell currentCell = cellInitializer.island.getCells(coordinate);
        Iterator<Herbivore> iteratorForHerbivores = currentCell.getHerbivoreList().iterator();
        while (iteratorForHerbivores.hasNext() && t.getWeight() <= BoaProperties.MAX_WEIGHT_BOA) {
            String className = iteratorForHerbivores.next().getClass().getName();
            if (Objects.equals(className, Rabbit.class.getName()) && ThreadLocalRandom.current().nextInt(101) <= BoaProperties.CHANCE_TO_EAT_RABBIT) {
                eatRabbit(t);
                iteratorForHerbivores.remove();
            } else if (Objects.equals(className,Mouse.class.getName()) && ThreadLocalRandom.current().nextInt(101) <= BoaProperties.CHANCE_TO_EAT_MOUSE) {
                eatMouse(t);
                iteratorForHerbivores.remove();
            } else if (Objects.equals(className,Duck.class.getName()) && ThreadLocalRandom.current().nextInt(101) <= BoaProperties.CHANCE_TO_EAT_DUCK) {
                eatDuck(t);
                iteratorForHerbivores.remove();
            } else {
                dietAnimal(t);
            }
        }
        Iterator<Predator> iteratorForPredators = currentCell.getPredatorList().iterator();
        while (iteratorForPredators.hasNext() && t.getWeight() <= BoaProperties.MAX_WEIGHT_BOA) {
            String className = iteratorForPredators.next().getClass().getName();
            if (Objects.equals(className,Fox.class.getName()) && ThreadLocalRandom.current().nextInt(101) <= BoaProperties.CHANCE_TO_EAT_FOX) {
                eatFox(t);
                iteratorForPredators.remove();
            } else {
                dietAnimal(t);
            }
        }
    }

    @Override
    public void bornOrganism(Coordinate coordinate) {
        if (ThreadLocalRandom.current().nextBoolean() && boaCountIsNotFull(coordinate)) {
            Animal newBreadedAnimal = new Boa(BoaProperties.MIN_WEIGHT_BOA);
            cellInitializer.initializeBreadedAnimalToCell(coordinate, newBreadedAnimal);
        }
    }

    private <T extends Animal> void dietAnimal(T t) {
        if (weightLoss(t) <= 0){
            t = null;
        }
    }

    private boolean boaCountIsNotFull(Coordinate coordinateForCount) {
        int boaCount = 0;

        for (Predator boasInPredators : cellInitializer.island.getCells(coordinateForCount).getPredatorList()) {
            if (boasInPredators instanceof Boa) {
                boaCount++;
            }
        }

        return boaCount < BoaProperties.MAX_COUNT_BOA;
    }

}
