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
import property.organismproperty.predatorproperty.BearProperties;
import property.organismproperty.predatorproperty.BoaProperties;
import property.util.BornOrganism;
import property.util.EatableAnimal;
import property.util.MovableAnimal;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Boa extends Predator implements MovableAnimal, EatableAnimal, BornOrganism {

    private final CellInitializer cellInitializer = new CellInitializer();

    public Boa(double weight) {
        super(weight);
    }

    @Override
    public <T extends Animal> void move(Coordinate coordinate, T t) {
        Coordinate newCoordinates = defineNewDirection(coordinate, BoaProperties.STEP);
        if (boaCountIsNotFull(newCoordinates)) {
            cellInitializer.moveAnimalToNewCoordinate(newCoordinates, coordinate, t);
        }
    }

    @Override
    public void eat(Coordinate coordinate) {
        Cell currentCell = cellInitializer.island.getCells(coordinate);
        if (this.getWeight() <= BearProperties.MAX_WEIGHT_BEAR && currentCell.getLock().tryLock()) {
            try {
                Iterator<Herbivore> iteratorForHerbivores = currentCell.getHerbivoreList().iterator();
                while (iteratorForHerbivores.hasNext()) {
                    String className = iteratorForHerbivores.next().getClass().getName();
                    if (Objects.equals(className, Rabbit.class.getName()) && ThreadLocalRandom.current().nextInt(101) <= BoaProperties.CHANCE_TO_EAT_RABBIT) {
                        eatRabbit(this);
                        iteratorForHerbivores.remove();
                    } else if (Objects.equals(className, Mouse.class.getName()) && ThreadLocalRandom.current().nextInt(101) <= BoaProperties.CHANCE_TO_EAT_MOUSE) {
                        eatMouse(this);
                        iteratorForHerbivores.remove();
                    } else if (Objects.equals(className, Duck.class.getName()) && ThreadLocalRandom.current().nextInt(101) <= BoaProperties.CHANCE_TO_EAT_DUCK) {
                        eatDuck(this);
                        iteratorForHerbivores.remove();
                    } else {
                        dietAnimal(coordinate);
                    }
                }

                if (currentCell.getPredatorList() != null) {
                    List<Predator> predators = currentCell.getPredatorList();
                    for (int i = 0; i < predators.size(); i++) {
                        Predator predator = predators.get(i);
                        if (predator != null && Fox.class.getName().equals(predator.getClass().getName()) &&
                                ThreadLocalRandom.current().nextInt(101) <= BoaProperties.MAX_WEIGHT_BOA) {
                            eatFox(this);
                            predators.remove(predator);
                        } else {
                            dietAnimal(coordinate);
                        }
                    }
                }
            } finally {
                currentCell.getLock().unlock();
            }
        }
    }

    @Override
    public void bornOrganism(Coordinate coordinate) {
        if (ThreadLocalRandom.current().nextBoolean() && boaCountIsNotFull(coordinate)) {
            Animal newBreadedAnimal;
            newBreadedAnimal = new Boa(BoaProperties.MIN_WEIGHT_BOA);
            cellInitializer.initializeBreadedAnimalToCell(coordinate, newBreadedAnimal);
        }
    }

    /** This method is same in other animal classes.
     * We can take it to interface and do that method default for all implement classes.
     * But for now we configured the island_model with threads in a pool. I don't want to take this method because
     * might be we will lose control on ThreadTaskManager. But further i will take a look on this application and
     * finish it.*/
    private synchronized void dietAnimal(Coordinate coordinate) {
        if (weightLoss(this) <= 0) {
            cellInitializer.getCellByCoordinates(coordinate).getPredatorList().remove(this);
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
