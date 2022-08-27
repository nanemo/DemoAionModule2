package entity.organism.herbivores;

import abstractions.Animal;
import abstractions.Herbivore;
import controller.Cell;
import controller.CellInitializer;
import controller.Coordinate;
import property.organismproperty.herbivoreproperty.SheepProperties;
import property.util.BornOrganism;
import property.util.EatableAnimal;
import property.util.MovableAnimal;

import java.util.concurrent.ThreadLocalRandom;

public class Sheep extends Herbivore implements MovableAnimal, EatableAnimal, BornOrganism {

    public Sheep(double weight) {
        super(weight);
    }

    private CellInitializer cellInitializer = new CellInitializer();

    @Override
    public <T extends Animal> void move(Coordinate coordinate, T t) {
        Coordinate newCoordinates = defineNewDirection(coordinate, SheepProperties.STEP);
        if (sheepCountIsNotFull(newCoordinates)) {
            cellInitializer.moveAnimalToNewCoordinate(newCoordinates, coordinate, t);
        }
    }

    @Override
    public synchronized <T extends Animal> void eat(Coordinate coordinate) {
        Cell currentCell = cellInitializer.island.getCells(coordinate);
        if (currentCell.getPlantList() != null) {
            while (!(currentCell.getPlantList().isEmpty()) && this.getWeight() <= SheepProperties.MAX_WEIGHT_SHEEP) {
                eatPlant(this);
                currentCell.getPlantList().remove(0);
            }
        } else {
            dietAnimal(coordinate, this);
        }
    }

    @Override
    public void bornOrganism(Coordinate coordinate) {
        if (ThreadLocalRandom.current().nextBoolean() && sheepCountIsNotFull(coordinate)) {
            Animal newBreadedAnimal = new Sheep(SheepProperties.MIN_WEIGHT_SHEEP);
            cellInitializer.initializeBreadedAnimalToCell(coordinate, newBreadedAnimal);
        }
    }

    /** This method is same in other animal classes.
     * We can take it to interface and do that method default for all implement classes.
     * But for now we configured the island_model with threads in a pool. I don't want to take this method because
     * might be we will lose control on ThreadTaskManager. But further i will take a look on this application and
     * finish it.*/
    private <T extends Animal> void dietAnimal(Coordinate coordinate, T t) {
        if (weightLoss(t) <= 0){
            cellInitializer.getCellByCoordinates(coordinate).getHerbivoreList().remove(t);
        }
    }

    private boolean sheepCountIsNotFull(Coordinate coordinateForCount) {
        int sheepCount = 0;

        for (Herbivore sheepInHerbivore : cellInitializer.island.getCells(coordinateForCount).getHerbivoreList()) {
            if (sheepInHerbivore instanceof Sheep) {
                sheepCount++;
            }
        }

        return sheepCount < SheepProperties.MAX_COUNT_SHEEP;
    }

}
