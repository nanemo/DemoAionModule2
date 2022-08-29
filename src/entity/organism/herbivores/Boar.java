package entity.organism.herbivores;

import abstractions.Animal;
import abstractions.Herbivore;
import controller.Cell;
import controller.CellInitializer;
import controller.Coordinate;
import property.organismproperty.herbivoreproperty.BoarProperties;
import property.util.BornOrganism;
import property.util.DietAnimal;
import property.util.EatableAnimal;
import property.util.MovableAnimal;

import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Boar extends Herbivore implements MovableAnimal, EatableAnimal, BornOrganism, DietAnimal {

    private final CellInitializer cellInitializer = new CellInitializer();

    public Boar(double weight) {
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
    public synchronized void eat(Coordinate coordinate) {
        Cell currentCell = cellInitializer.island.getCells(coordinate);
        Iterator<Herbivore> iteratorForHerbivores = currentCell.getHerbivoreList().iterator();

        while (iteratorForHerbivores.hasNext() && this.getWeight() <= BoarProperties.MAX_WEIGHT_BOAR) {
            String className = iteratorForHerbivores.next().getClass().getName();
            if (Objects.equals(className, Mouse.class.getName()) && ThreadLocalRandom.current().
                    nextInt(101) <= BoarProperties.CHANCE_TO_EAT_MOUSE) {
                eatMouse(this);
                iteratorForHerbivores.remove();
            } else if (Objects.equals(className, Caterpillar.class.getName()) && ThreadLocalRandom.current().
                    nextInt(101) <= BoarProperties.CHANCE_TO_EAT_CATERPILLAR) {
                eatCaterpillar(this);
                iteratorForHerbivores.remove();
            } else {
                dietAnimal(coordinate, this);
            }
        }

        if (currentCell.getPlantList() != null) {
            while (!(currentCell.getPlantList().isEmpty()) && this.getWeight() <= BoarProperties.MAX_WEIGHT_BOAR) {
                eatPlant(this);
                currentCell.getPlantList().remove(0);
            }
        } else {
            dietAnimal(coordinate, this);
        }
    }

    @Override
    public void bornOrganism(Coordinate coordinate) {
        if (ThreadLocalRandom.current().nextBoolean() && boarCountIsNotFull(coordinate)) {
            Animal newBreadedAnimal = new Boar(BoarProperties.MIN_WEIGHT_BOAR);
            cellInitializer.initializeBreadedAnimalToCell(coordinate, newBreadedAnimal);
        }
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
