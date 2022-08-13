package entity.organism.herbivores;

import abstractions.Animal;
import abstractions.Herbivore;
import controller.Cell;
import controller.CellInitializer;
import controller.Coordinate;
import entity.organism.plants.Plant;
import property.organismproperty.herbivoreproperty.DeerProperties;
import property.util.BornOrganism;
import property.util.EatableAnimal;
import property.util.MovableAnimal;

import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class Deer extends Herbivore implements MovableAnimal, EatableAnimal, BornOrganism {

    public Deer(double weight) {
        super(weight);
    }

    private CellInitializer cellInitializer = new CellInitializer();

    @Override
    public <T extends Animal> void move(Coordinate coordinate, T t) {
        Coordinate newCoordinates = defineNewDirection(coordinate, DeerProperties.STEP);
        if (deerCountIsNotFull(newCoordinates)) {
            cellInitializer.moveAnimalToNewCoordinate(newCoordinates, coordinate, t);
        }
    }

    @Override
    public <T extends Animal> void eat(Coordinate coordinate, T t) {
        Cell currentCell = cellInitializer.island.getCells(coordinate);
        if (currentCell.getPlantList() != null && ThreadLocalRandom.current().nextInt(DeerProperties.CHANCE_TO_EAT_PLANT) == 0) {
            Iterator<Plant> iteratorForPlant = currentCell.getPlantList().iterator();
            while (iteratorForPlant.hasNext() && t.getWeight() <= DeerProperties.MAX_WEIGHT_DEER) {
                eatPlant(t);
                iteratorForPlant.remove();
            }
        }
    }

    @Override
    public void bornOrganism(Coordinate coordinate) {
        if (ThreadLocalRandom.current().nextBoolean() && deerCountIsNotFull(coordinate)) {
            Animal newBreadedAnimal = new Deer(DeerProperties.MIN_WEIGHT_DEER);
            cellInitializer.initializeBreadedAnimalToCell(coordinate, newBreadedAnimal);
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
