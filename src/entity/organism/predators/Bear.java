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
import java.util.Objects;
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
            String className = iteratorForHerbivores.next().getClass().getName();
            if (Objects.equals(className, Horse.class.getName()) && ThreadLocalRandom.current().
                    nextInt(101) <= BearProperties.CHANCE_TO_EAT_HORSE) {
                eatHorse(t);
                iteratorForHerbivores.remove();
            } else if (Objects.equals(className, Deer.class.getName()) && ThreadLocalRandom.current().
                    nextInt(101) <= BearProperties.CHANCE_TO_EAT_DEER) {
                eatDeer(t);
                iteratorForHerbivores.remove();
            } else if (Objects.equals(className, Rabbit.class.getName()) && ThreadLocalRandom.current().
                    nextInt(101) <= BearProperties.CHANCE_TO_EAT_RABBIT) {
                eatRabbit(t);
                iteratorForHerbivores.remove();
            } else if (Objects.equals(className, Mouse.class.getName()) && ThreadLocalRandom.current().
                    nextInt(101) <= BearProperties.CHANCE_TO_EAT_MOUSE) {
                eatMouse(t);
                iteratorForHerbivores.remove();
            } else if (Objects.equals(className, Goat.class.getName()) && ThreadLocalRandom.current().
                    nextInt(101) <= BearProperties.CHANCE_TO_EAT_GOAT) {
                eatGoat(t);
                iteratorForHerbivores.remove();
            } else if (Objects.equals(className, Sheep.class.getName()) && ThreadLocalRandom.current().
                    nextInt(101) <= BearProperties.CHANCE_TO_EAT_SHEEP) {
                eatSheep(t);
                iteratorForHerbivores.remove();
            } else if (Objects.equals(className, Boar.class.getName()) && ThreadLocalRandom.current().
                    nextInt(101) <= BearProperties.CHANCE_TO_EAT_BOAR) {
                eatBoar(t);
                iteratorForHerbivores.remove();
            } else if (Objects.equals(className, Buffalo.class.getName()) && ThreadLocalRandom.current().
                    nextInt(101) <= BearProperties.CHANCE_TO_EAT_BUFFALO) {
                eatBuffalo(t);
                iteratorForHerbivores.remove();
            } else if (Objects.equals(className, Duck.class.getName()) && ThreadLocalRandom.current().
                    nextInt(101) <= BearProperties.CHANCE_TO_EAT_DUCK) {
                eatDuck(t);
                iteratorForHerbivores.remove();
            } else {
                dietAnimal(t);
            }
        }
        Iterator<Predator> iteratorForPredators = currentCell.getPredatorList().iterator();
        while (iteratorForPredators.hasNext() && t.getWeight() <= BearProperties.MAX_WEIGHT_BEAR) {
            String className = iteratorForPredators.next().getClass().getName();
            if (Objects.equals(className, Boa.class.getName()) && ThreadLocalRandom.current().nextInt(101) <= BearProperties.CHANCE_TO_EAT_BOA) {
                eatBoa(t);
                iteratorForPredators.remove();
            } else {
                dietAnimal(t);
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

    private <T extends Animal> void dietAnimal(T t) {
        if (weightLoss(t) <= 0){
            t = null;
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
