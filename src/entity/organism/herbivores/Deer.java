package entity.organism.herbivores;

import abstractions.Animal;
import abstractions.Herbivore;
import controller.Cell;
import controller.CellInitializer;
import controller.Coordinate;
import property.organismproperty.herbivoreproperty.DeerProperties;
import property.util.BornOrganism;
import property.util.EatableAnimal;
import property.util.MovableAnimal;

import java.util.concurrent.ThreadLocalRandom;

public class Deer extends Herbivore implements MovableAnimal, EatableAnimal, BornOrganism {

    public Deer(double weight) {
        super(weight);
    }

    private final CellInitializer cellInitializer = new CellInitializer();

    @Override
    public synchronized <T extends Animal> void move(Coordinate coordinate, T t) {
        Coordinate newCoordinates = defineNewDirection(coordinate, DeerProperties.STEP);

        if (deerCountIsNotFull(newCoordinates)) {
            cellInitializer.moveAnimalToNewCoordinate(newCoordinates, coordinate, t);
        }
    }

    @Override
    public synchronized void eat(Coordinate coordinate) {
        Cell currentCell = cellInitializer.island.getCells(coordinate);

        if (currentCell.getPlantList() != null) {
            while (!(currentCell.getPlantList().isEmpty()) && this.getWeight() <= DeerProperties.MAX_WEIGHT_DEER) {
                eatPlant(this);
                currentCell.getPlantList().remove(0);
            }
        } else {
            dietAnimal(coordinate, this);
        }
    }

    @Override
    public void bornOrganism(Coordinate coordinate) {
        if (ThreadLocalRandom.current().nextBoolean() && deerCountIsNotFull(coordinate)) {
            Animal newBreadedAnimal = new Deer(DeerProperties.MIN_WEIGHT_DEER);
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

    private boolean deerCountIsNotFull(Coordinate coordinateForCount) {
        int deerCount = 0;

        for (Herbivore deersInHerbivore : cellInitializer.island.getCells(coordinateForCount).getHerbivoreList()) {
            if (deersInHerbivore instanceof Deer) {
                deerCount++;
            }
        }

        return deerCount < DeerProperties.MAX_COUNT_DEER;
    }

}
