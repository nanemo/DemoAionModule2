package entity.organism.herbivores;

import abstractions.Animal;
import abstractions.Herbivore;
import controller.Cell;
import controller.CellInitializer;
import controller.Coordinate;
import entity.organism.plants.Plant;
import property.organismproperty.herbivoreproperty.BuffaloProperties;
import property.util.BornOrganism;
import property.util.EatableAnimal;
import property.util.MovableAnimal;

import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class Buffalo extends Herbivore implements MovableAnimal, EatableAnimal, BornOrganism {

    public Buffalo(double weight) {
        super(weight);
    }

    private CellInitializer cellInitializer = new CellInitializer();

    @Override
    public <T extends Animal> void move(Coordinate coordinate, T t) {
        Coordinate newCoordinates = defineNewDirection(coordinate, BuffaloProperties.STEP);
        if (buffaloCountIsNotFull(newCoordinates)) {
            cellInitializer.moveAnimalToNewCoordinate(newCoordinates, coordinate, t);
        }
    }

    @Override
    public <T extends Animal> void eat(Coordinate coordinate, T t) {
        Cell currentCell = cellInitializer.island.getCells(coordinate);
        if (currentCell.getPlantList() != null && ThreadLocalRandom.current().nextInt(BuffaloProperties.CHANCE_TO_EAT_PLANT) == 0) {
            Iterator<Plant> iteratorForPlant = currentCell.getPlantList().iterator();
            while (iteratorForPlant.hasNext() && t.getWeight() <= BuffaloProperties.MAX_WEIGHT_BUFFALO) {
                eatPlant(t);
                iteratorForPlant.remove();
            }
        }
    }

    @Override
    public void bornOrganism(Coordinate coordinate) {
        if (ThreadLocalRandom.current().nextBoolean() && buffaloCountIsNotFull(coordinate)) {
            Animal newBreadedAnimal = new Buffalo(BuffaloProperties.MIN_WEIGHT_BUFFALO);
            cellInitializer.initializeBreadedAnimalToCell(coordinate, newBreadedAnimal);
        }
    }

    private boolean buffaloCountIsNotFull(Coordinate coordinateForCount) {
        int buffaloCount = 0;

        for (Herbivore buffaloInHerbivore : cellInitializer.island.getCells(coordinateForCount).getHerbivoreList()) {
            if (buffaloInHerbivore instanceof Buffalo) {
                buffaloCount++;
            }
        }

        return buffaloCount < BuffaloProperties.MAX_COUNT_BUFFALO;
    }

}
