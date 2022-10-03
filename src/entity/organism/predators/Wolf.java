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
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Wolf extends Predator implements MovableAnimal, EatableAnimal, BornOrganism {

    public Wolf(double weight) {
        super(weight);
    }

    private final CellInitializer cellInitializer = new CellInitializer();

    @Override
    public <T extends Animal> void move(Coordinate coordinate, T t) {
        Coordinate newCoordinates = defineNewDirection(coordinate, WolfProperties.STEP);

        if (wolfCountIsNotFull(newCoordinates)) {
            cellInitializer.moveAnimalToNewCoordinate(newCoordinates, coordinate, t);
        }
    }

    @Override
    public void eat(Coordinate coordinate) {
        Cell currentCell = cellInitializer.island.getCells(coordinate);
        Iterator<Herbivore> iteratorForHerbivores = currentCell.getHerbivoreList().iterator();

        while (iteratorForHerbivores.hasNext() && this.getWeight() <= WolfProperties.MAX_WEIGHT_WOLF) {
            String className = iteratorForHerbivores.next().getClass().getName();
            if (Objects.equals(className, Horse.class.getName()) && ThreadLocalRandom.current().
                    nextInt(101) <= WolfProperties.CHANCE_TO_EAT_HORSE) {
                eatHorse(this);
                iteratorForHerbivores.remove();
            } else if (Objects.equals(className, Deer.class.getName()) && ThreadLocalRandom.current().
                    nextInt(101) <= WolfProperties.CHANCE_TO_EAT_DEER) {
                eatDeer(this);
                iteratorForHerbivores.remove();
            } else if (Objects.equals(className, Rabbit.class.getName()) && ThreadLocalRandom.current().
                    nextInt(101) <= WolfProperties.CHANCE_TO_EAT_RABBIT) {
                eatRabbit(this);
                iteratorForHerbivores.remove();
            } else if (Objects.equals(className, Mouse.class.getName()) && ThreadLocalRandom.current().
                    nextInt(101) <= WolfProperties.CHANCE_TO_EAT_MOUSE) {
                eatMouse(this);
                iteratorForHerbivores.remove();
            } else if (Objects.equals(className, Goat.class.getName()) && ThreadLocalRandom.current().
                    nextInt(101) <= WolfProperties.CHANCE_TO_EAT_GOAT) {
                eatGoat(this);
                iteratorForHerbivores.remove();
            } else if (Objects.equals(className, Sheep.class.getName()) && ThreadLocalRandom.current().
                    nextInt(101) <= WolfProperties.CHANCE_TO_EAT_SHEEP) {
                eatSheep(this);
                iteratorForHerbivores.remove();
            } else if (Objects.equals(className, Boar.class.getName()) && ThreadLocalRandom.current().
                    nextInt(101) <= WolfProperties.CHANCE_TO_EAT_BOAR) {
                eatBoar(this);
                iteratorForHerbivores.remove();
            } else if (Objects.equals(className, Buffalo.class.getName()) && ThreadLocalRandom.current().
                    nextInt(101) <= WolfProperties.CHANCE_TO_EAT_BUFFALO) {
                eatBuffalo(this);
                iteratorForHerbivores.remove();
            } else if (Objects.equals(className, Duck.class.getName()) && ThreadLocalRandom.current().
                    nextInt(101) <= WolfProperties.CHANCE_TO_EAT_DUCK) {
                eatDuck(this);
                iteratorForHerbivores.remove();
            } else {
                dietAnimal(coordinate, this);
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
