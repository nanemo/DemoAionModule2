package entity.organism.herbivores;

import abstractions.Animal;
import abstractions.Herbivore;
import controller.Cell;
import controller.CellInitializer;
import controller.Coordinate;
import property.organismproperty.herbivoreproperty.GoatProperties;
import property.util.BornOrganism;
import property.util.EatableAnimal;
import property.util.MovableAnimal;

import java.util.concurrent.ThreadLocalRandom;

public class Goat extends Herbivore implements MovableAnimal, EatableAnimal, BornOrganism {

    public Goat(double weight) {
        super(weight);
    }

    private CellInitializer cellInitializer = new CellInitializer();

    @Override
    public <T extends Animal> void move(Coordinate coordinate, T t) {
        Coordinate newCoordinates = defineNewDirection(coordinate, GoatProperties.STEP);
        if (goatCountIsNotFull(newCoordinates)) {
            cellInitializer.moveAnimalToNewCoordinate(newCoordinates, coordinate, t);
        }
    }

    @Override
    public <T extends Animal> void eat(Coordinate coordinate, T t) {
        Cell currentCell = cellInitializer.island.getCells(coordinate);
        if (currentCell.getPlantList() != null ) {
            while (!(currentCell.getPlantList().isEmpty()) && t.getWeight() <= GoatProperties.MAX_WEIGHT_GOAT) {
                eatPlant(t);
                currentCell.getPlantList().remove(0);
            }
        } else {
            dietAnimal(coordinate, t);
        }
    }

    @Override
    public void bornOrganism(Coordinate coordinate) {
        if (ThreadLocalRandom.current().nextBoolean() && goatCountIsNotFull(coordinate)) {
            Animal newBreadedAnimal = new Goat(GoatProperties.MIN_WEIGHT_GOAT);
            cellInitializer.initializeBreadedAnimalToCell(coordinate, newBreadedAnimal);
        }
    }

    private <T extends Animal> void dietAnimal(Coordinate coordinate, T t) {
        if (weightLoss(t) <= 0){
            cellInitializer.getCellByCoordinates(coordinate).getHerbivoreList().remove(t);
        }
    }

    private boolean goatCountIsNotFull(Coordinate coordinateForCount) {
        int goatCount = 0;

        for (Herbivore goatsInHerbivore : cellInitializer.island.getCells(coordinateForCount).getHerbivoreList()) {
            if (goatsInHerbivore instanceof Goat) {
                goatCount++;
            }
        }

        return goatCount < GoatProperties.MAX_COUNT_GOAT;
    }
}
