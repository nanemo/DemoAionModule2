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
            String className = iteratorForHerbivores.next().getClass().getName();
            if (Objects.equals(className,Horse.class.getName()) && ThreadLocalRandom.current().nextInt(101) <= WolfProperties.CHANCE_TO_EAT_HORSE) {
                eatHorse(t);
                iteratorForHerbivores.remove();
            } else if (Objects.equals(className,Deer.class.getName()) && ThreadLocalRandom.current().nextInt(101) <= WolfProperties.CHANCE_TO_EAT_DEER) {
                eatDeer(t);
                iteratorForHerbivores.remove();
            } else if (Objects.equals(className,Rabbit.class.getName()) && ThreadLocalRandom.current().nextInt(101) <= WolfProperties.CHANCE_TO_EAT_RABBIT) {
                eatRabbit(t);
                iteratorForHerbivores.remove();
            } else if (Objects.equals(className,Mouse.class.getName()) && ThreadLocalRandom.current().nextInt(101) <= WolfProperties.CHANCE_TO_EAT_MOUSE) {
                eatMouse(t);
                iteratorForHerbivores.remove();
            } else if (Objects.equals(className,Goat.class.getName()) && ThreadLocalRandom.current().nextInt(101) <= WolfProperties.CHANCE_TO_EAT_GOAT) {
                eatGoat(t);
                iteratorForHerbivores.remove();
            } else if (Objects.equals(className,Sheep.class.getName()) && ThreadLocalRandom.current().nextInt(101) <= WolfProperties.CHANCE_TO_EAT_SHEEP) {
                eatSheep(t);
                iteratorForHerbivores.remove();
            } else if (Objects.equals(className,Boar.class.getName()) && ThreadLocalRandom.current().nextInt(101) <= WolfProperties.CHANCE_TO_EAT_BOAR) {
                eatBoar(t);
                iteratorForHerbivores.remove();
            } else if (Objects.equals(className,Buffalo.class.getName()) && ThreadLocalRandom.current().nextInt(101) <= WolfProperties.CHANCE_TO_EAT_BUFFALO) {
                eatBuffalo(t);
                iteratorForHerbivores.remove();
            } else if (Objects.equals(className,Duck.class.getName()) && ThreadLocalRandom.current().nextInt(101) <= WolfProperties.CHANCE_TO_EAT_DUCK) {
                eatDuck(t);
                iteratorForHerbivores.remove();
            } else {
                dietAnimal(t);
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

    private <T extends Animal> void dietAnimal(T t) {
        if (weightLoss(t) <= 0){
            t = null;
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
