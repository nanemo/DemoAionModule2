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
            String className = iteratorForHerbivores.next().getClass().getName();
            if (Objects.equals(className, Caterpillar.class.getName()) && ThreadLocalRandom.current().nextInt(101) <= MouseProperties.CHANCE_TO_EAT_CATERPILLAR) {
                eatCaterpillar(t);
                iteratorForHerbivores.remove();
            } else {
                dietAnimal(coordinate, t);
            }
        }
        if (currentCell.getPlantList() != null) {
            while (!(currentCell.getPlantList().isEmpty()) && t.getWeight() <= DuckProperties.MAX_WEIGHT_DUCK) {
                eatPlant(t);
                currentCell.getPlantList().remove(0);
            }
        } else {
            dietAnimal(coordinate, t);
        }
    }

    @Override
    public void bornOrganism(Coordinate coordinate) {
        if (ThreadLocalRandom.current().nextBoolean()) {
            Animal newBreadedAnimal = new Duck(DuckProperties.MIN_WEIGHT_DUCK);
            cellInitializer.initializeBreadedAnimalToCell(coordinate, newBreadedAnimal);
        }
    }

    private <T extends Animal> void dietAnimal(Coordinate coordinate, T t) {
        if (weightLoss(t) <= 0){
            cellInitializer.getCellByCoordinates(coordinate).getHerbivoreList().remove(t);
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
