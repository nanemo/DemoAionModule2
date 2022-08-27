package entity.organism.herbivores;

import abstractions.Animal;
import abstractions.Herbivore;
import controller.Cell;
import controller.CellInitializer;
import controller.Coordinate;
import property.organismproperty.herbivoreproperty.DuckProperties;
import property.organismproperty.herbivoreproperty.MouseProperties;
import property.util.BornOrganism;
import property.util.EatableAnimal;
import property.util.MovableAnimal;

import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Duck extends Herbivore implements MovableAnimal, EatableAnimal, BornOrganism {

    private final CellInitializer cellInitializer = new CellInitializer();

    public Duck(double weight) {
        super(weight);
    }

    @Override
    public <T extends Animal> void move(Coordinate coordinate, T t) {
        Coordinate newCoordinates = defineNewDirection(coordinate, DuckProperties.STEP);

        if (duckCountIsNotFull(newCoordinates)) {
            cellInitializer.moveAnimalToNewCoordinate(newCoordinates, coordinate, t);
        }
    }

    @Override
    public synchronized void eat(Coordinate coordinate) {
        Cell currentCell = cellInitializer.island.getCells(coordinate);
        Iterator<Herbivore> iteratorForHerbivores = currentCell.getHerbivoreList().iterator();

        while (iteratorForHerbivores.hasNext() && this.getWeight() <= DuckProperties.MAX_WEIGHT_DUCK) {
            if (Objects.equals(iteratorForHerbivores.next().getClass().getName(), Caterpillar.class.getName()) &&
                    ThreadLocalRandom.current().nextInt(101) <= MouseProperties.CHANCE_TO_EAT_CATERPILLAR) {
                eatCaterpillar(this);
                iteratorForHerbivores.remove();
            } else if (currentCell.getPlantList() != null) {
                while (!(currentCell.getPlantList().isEmpty()) &&
                        this.getWeight() <= DuckProperties.MAX_WEIGHT_DUCK) {
                    eatPlant(this);
                    currentCell.getPlantList().remove(0);
                }
            } else {
                dietAnimal(coordinate, this);
            }
        }
    }

    @Override
    public void bornOrganism(Coordinate coordinate) {
        if (ThreadLocalRandom.current().nextBoolean()) {
            Animal newBreadedAnimal = new Duck(DuckProperties.MIN_WEIGHT_DUCK);
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
            cellInitializer.getCellByCoordinates(coordinate).getHerbivoreList().removeIf(herbivore -> herbivore == t);
        }
    }

    private boolean duckCountIsNotFull(Coordinate coordinateForCount) {
        int duckCount = 0;

        for (Herbivore ducksInHerbivore : cellInitializer.island.getCells(coordinateForCount).getHerbivoreList()) {
            if (ducksInHerbivore instanceof Duck) {
                duckCount++;
            }
        }

        return duckCount < DuckProperties.MAX_COUNT_DUCK;
    }

}
