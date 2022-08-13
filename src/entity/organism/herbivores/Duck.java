package entity.organism.herbivores;

import abstractions.Animal;
import abstractions.Herbivore;
import controller.Cell;
import controller.CellInitializer;
import controller.Coordinate;
import entity.organism.plants.Plant;
import property.organismproperty.herbivoreproperty.DuckProperties;
import property.util.BornOrganism;
import property.util.EatableAnimal;
import property.util.MovableAnimal;

import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class Duck extends Herbivore implements MovableAnimal, EatableAnimal, BornOrganism {

    public Duck(double weight) {
        super(weight);
    }

    private CellInitializer cellInitializer = new CellInitializer();

    @Override
    public <T extends Animal> void move(Coordinate coordinate, T t) {
        Coordinate newCoordinates = defineNewDirection(coordinate, DuckProperties.STEP);
        if (duckCountIsNotFull(newCoordinates)) {
            cellInitializer.moveAnimalToNewCoordinate(newCoordinates, coordinate, t);
        }
    }

    @Override
    public <T extends Animal> void eat(Coordinate coordinate, T t) {

        Cell currentCell = cellInitializer.island.getCells(coordinate);
        Iterator<Herbivore> iteratorForHerbivores = currentCell.getHerbivoreList().iterator();
        while (iteratorForHerbivores.hasNext() && t.getWeight() <= DuckProperties.MAX_WEIGHT_DUCK) {
            if (iteratorForHerbivores.next() instanceof Caterpillar && ThreadLocalRandom.current().nextInt(DuckProperties.CHANCE_TO_EAT_CATERPILLAR) == 0) {
                eatCaterpillar(t);
                iteratorForHerbivores.remove();
            }
        }
        if (currentCell.getPlantList() != null && ThreadLocalRandom.current().nextInt(DuckProperties.CHANCE_TO_EAT_PLANT) == 0) {
            Iterator<Plant> iteratorForPlant = currentCell.getPlantList().iterator();
            while (iteratorForPlant.hasNext() && t.getWeight() <= DuckProperties.MAX_WEIGHT_DUCK) {
                eatPlant(t);
                iteratorForPlant.remove();
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
