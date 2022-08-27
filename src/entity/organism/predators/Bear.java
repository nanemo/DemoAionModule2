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

    private final CellInitializer cellInitializer = new CellInitializer();

    @Override
    public <T extends Animal> void move(Coordinate coordinate, T t) {
        Coordinate newCoordinates = defineNewDirection(coordinate, BearProperties.STEP);

        if (bearCountIsNotFull(newCoordinates)) {
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
                    if (Objects.equals(className, Horse.class.getName()) && ThreadLocalRandom.current().
                            nextInt(101) <= BearProperties.CHANCE_TO_EAT_HORSE) {
                        eatHorse(this);
                        iteratorForHerbivores.remove();
                    } else if (Objects.equals(className, Deer.class.getName()) && ThreadLocalRandom.current().
                            nextInt(101) <= BearProperties.CHANCE_TO_EAT_DEER) {
                        eatDeer(this);
                        iteratorForHerbivores.remove();
                    } else if (Objects.equals(className, Rabbit.class.getName()) && ThreadLocalRandom.current().
                            nextInt(101) <= BearProperties.CHANCE_TO_EAT_RABBIT) {
                        eatRabbit(this);
                        iteratorForHerbivores.remove();
                    } else if (Objects.equals(className, Mouse.class.getName()) && ThreadLocalRandom.current().
                            nextInt(101) <= BearProperties.CHANCE_TO_EAT_MOUSE) {
                        eatMouse(this);
                        iteratorForHerbivores.remove();
                    } else if (Objects.equals(className, Goat.class.getName()) && ThreadLocalRandom.current().
                            nextInt(101) <= BearProperties.CHANCE_TO_EAT_GOAT) {
                        eatGoat(this);
                        iteratorForHerbivores.remove();
                    } else if (Objects.equals(className, Sheep.class.getName()) && ThreadLocalRandom.current().
                            nextInt(101) <= BearProperties.CHANCE_TO_EAT_SHEEP) {
                        eatSheep(this);
                        iteratorForHerbivores.remove();
                    } else if (Objects.equals(className, Boar.class.getName()) && ThreadLocalRandom.current().
                            nextInt(101) <= BearProperties.CHANCE_TO_EAT_BOAR) {
                        eatBoar(this);
                        iteratorForHerbivores.remove();
                    } else if (Objects.equals(className, Buffalo.class.getName()) && ThreadLocalRandom.current().
                            nextInt(101) <= BearProperties.CHANCE_TO_EAT_BUFFALO) {
                        eatBuffalo(this);
                        iteratorForHerbivores.remove();
                    } else if (Objects.equals(className, Duck.class.getName()) && ThreadLocalRandom.current().
                            nextInt(101) <= BearProperties.CHANCE_TO_EAT_DUCK) {
                        eatDuck(this);
                        iteratorForHerbivores.remove();
                    } else {
                        dietAnimal(coordinate, this);
                    }
                }
                if (currentCell.getPredatorList() != null) {
                    Iterator<Predator> iteratorForPredators = currentCell.getPredatorList().iterator();
                    while (iteratorForPredators.hasNext() && this.getWeight() <= BearProperties.MAX_WEIGHT_BEAR) {
                        if (Objects.equals(iteratorForPredators.next().getClass().getName(), Boa.class.getName()) && ThreadLocalRandom.current().nextInt(101) <= BearProperties.CHANCE_TO_EAT_BOA) {
                            eatBoa(this);
                            iteratorForPredators.remove();
                        } else {
                            dietAnimal(coordinate, this);
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
        if (ThreadLocalRandom.current().nextBoolean() && bearCountIsNotFull(coordinate)) {
            Animal newBreadedAnimal = new Bear(BearProperties.MIN_WEIGHT_BEAR);
            cellInitializer.initializeBreadedAnimalToCell(coordinate, newBreadedAnimal);
        }
    }

    /** This method is same in other animal classes.
     * We can take it to interface and do that method default for all implement classes.
     * But for now we configured the island_model with threads in a pool. I don't want to take this method because
     * might be we will lose control on ThreadTaskManager. But further i will take a look on this application and
     * finish it.*/
    private <T extends Animal> void dietAnimal(Coordinate coordinate, T t) {
        if (weightLoss(t) <= 0) {
            cellInitializer.getCellByCoordinates(coordinate).getPredatorList().remove(t);
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
