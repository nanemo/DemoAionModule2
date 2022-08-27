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
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Fox extends Predator implements MovableAnimal, EatableAnimal, BornOrganism {

    public Fox(double weight) {
        super(weight);
    }

    private final CellInitializer cellInitializer = new CellInitializer();

    @Override
    public <T extends Animal> void move(Coordinate coordinate, T t) {
        Coordinate newCoordinates = defineNewDirection(coordinate, FoxProperties.STEP);
        if (foxCountIsNotFull(newCoordinates)) {
            cellInitializer.moveAnimalToNewCoordinate(newCoordinates, coordinate, t);
        }
    }

    @Override
    public synchronized void eat(Coordinate coordinate) {
        Cell currentCell = cellInitializer.island.getCells(coordinate);
        Iterator<Herbivore> iteratorForHerbivores = currentCell.getHerbivoreList().iterator();

        while (iteratorForHerbivores.hasNext() && this.getWeight() <= FoxProperties.MAX_WEIGHT_FOX) {
            String className = iteratorForHerbivores.next().getClass().getName();
            if (Objects.equals(className,Rabbit.class.getName()) && ThreadLocalRandom.current().nextInt(101) <= FoxProperties.CHANCE_TO_EAT_RABBIT) {
                eatRabbit(this);
                iteratorForHerbivores.remove();
            } else if (Objects.equals(className,Mouse.class.getName()) && ThreadLocalRandom.current().nextInt(101) <= FoxProperties.CHANCE_TO_EAT_MOUSE) {
                eatMouse(this);
                iteratorForHerbivores.remove();
            } else if (Objects.equals(className,Duck.class.getName()) && ThreadLocalRandom.current().nextInt(101) <= FoxProperties.CHANCE_TO_EAT_DUCK) {
                eatDuck(this);
                iteratorForHerbivores.remove();
            } else if (Objects.equals(className,Caterpillar.class.getName()) && ThreadLocalRandom.current().nextInt(101) <= FoxProperties.CHANCE_TO_EAT_CATERPILLAR) {
                eatCaterpillar(this);
                iteratorForHerbivores.remove();
            } else {
                dietAnimal(coordinate);
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

    /** This method is same in other animal classes.
     * We can take it to interface and do that method default for all implement classes.
     * But for now we configured the island_model with threads in a pool. I don't want to take this method because
     * might be we will lose control on ThreadTaskManager. But further i will take a look on this application and
     * finish it.*/
    private synchronized void dietAnimal(Coordinate coordinate) {
        if (weightLoss(this) <= 0){
            System.out.println(cellInitializer.getCellByCoordinates(coordinate).getPredatorList().remove(this));
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
