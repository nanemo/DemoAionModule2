package entity.organism.herbivores;

import abstractions.Animal;
import abstractions.Herbivore;
import controller.Cell;
import controller.CellInitializer;
import controller.Coordinate;
import entity.organism.plants.Plant;
import property.organismproperty.herbivoreproperty.BoarProperties;
import property.util.BornOrganism;
import property.util.DietAnimal;
import property.util.EatableAnimal;
import property.util.MovableAnimal;

import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;


public class Boar extends Herbivore implements MovableAnimal, EatableAnimal, BornOrganism, DietAnimal {

    private CellInitializer cellInitializer = new CellInitializer();

    public Boar(Double weight) {
        super(weight);
    }

    @Override
    public <T extends Animal> void move(Coordinate coordinate, T t) {
        Coordinate newCoordinates = defineNewDirection(coordinate, BoarProperties.STEP);
        if (boarCountIsNotFull(newCoordinates)) {
            cellInitializer.moveAnimalToNewCoordinate(newCoordinates, coordinate, t);
        }
    }

    @Override
    public <T extends Animal> void eat(Coordinate coordinate, T t) {
        Cell currentCell = cellInitializer.island.getCells(coordinate);

        Iterator<Herbivore> iteratorForHerbivores = currentCell.getHerbivoreList().iterator();
        while (iteratorForHerbivores.hasNext() && t.getWeight() <= BoarProperties.MAX_WEIGHT_BOAR) {
            if (iteratorForHerbivores.next() instanceof Mouse && ThreadLocalRandom.current().nextInt(BoarProperties.CHANCE_TO_EAT_MOUSE) == 0) {
                eatMouse(t);
                iteratorForHerbivores.remove();
            } else if (iteratorForHerbivores.next() instanceof Caterpillar && ThreadLocalRandom.current().nextInt(BoarProperties.CHANCE_TO_EAT_CATERPILLAR) == 0) {
                eatCaterpillar(t);
                iteratorForHerbivores.remove();
            }
        }

        if (currentCell.getPlantList() != null && ThreadLocalRandom.current().nextInt(BoarProperties.CHANCE_TO_EAT_PLANT) == 0) {
            Iterator<Plant> iteratorForPlant = currentCell.getPlantList().iterator();
            while (iteratorForPlant.hasNext() && t.getWeight() <= BoarProperties.MAX_WEIGHT_BOAR) {
                eatPlant(t);
                iteratorForPlant.remove();
            }
        }
    }

    @Override
    public void bornOrganism(Coordinate coordinate) {
        if (ThreadLocalRandom.current().nextBoolean() && boarCountIsNotFull(coordinate)) {
            Animal newBreadedAnimal = new Boar(BoarProperties.MIN_WEIGHT_BOAR);
            cellInitializer.initializeBreadedAnimalToCell(coordinate, newBreadedAnimal);
        }
    }

    public <T extends Animal> void dietAnimal(T t) {
        weightLoss(t);
    }

    private boolean boarCountIsNotFull(Coordinate coordinateForCount) {
        int boarCount = 0;

        for (Herbivore boarsInHerbivore : cellInitializer.island.getCells(coordinateForCount).getHerbivoreList()) {
            if (boarsInHerbivore instanceof Boar) {
                boarCount++;
            }
        }

        return boarCount < BoarProperties.MAX_BOAR_COUNT;
    }
}
