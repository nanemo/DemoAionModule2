package entity.organism.herbivores;

import abstractions.Animal;
import abstractions.Herbivore;
import controller.Cell;
import controller.CellInitializer;
import controller.Coordinate;
import entity.organism.plants.Plant;
import property.organismproperty.herbivoreproperty.MouseProperties;
import property.util.BornOrganism;
import property.util.EatableAnimal;
import property.util.MovableAnimal;

import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class Mouse extends Herbivore implements MovableAnimal, EatableAnimal, BornOrganism {

    public Mouse(double weight) {
        super(weight);
    }

    private CellInitializer cellInitializer = new CellInitializer();

    @Override
    public <T extends Animal> void move(Coordinate coordinate, T t) {
        Coordinate newCoordinates = defineNewDirection(coordinate, MouseProperties.STEP);
        if (mouseCountIsNotFull(newCoordinates)) {
            cellInitializer.moveAnimalToNewCoordinate(newCoordinates, coordinate, t);
        }
    }

    @Override
    public <T extends Animal> void eat(Coordinate coordinate, T t) {
        Cell currentCell = cellInitializer.island.getCells(coordinate);

        Iterator<Herbivore> iteratorForHerbivores = currentCell.getHerbivoreList().iterator();
        while (iteratorForHerbivores.hasNext() && t.getWeight() <= MouseProperties.MAX_WEIGHT_MOUSE) {
            if (iteratorForHerbivores.next() instanceof Caterpillar && ThreadLocalRandom.current().nextInt(MouseProperties.CHANCE_TO_EAT_CATERPILLAR) == 0) {
                eatCaterpillar(t);
                iteratorForHerbivores.remove();
            }
        }

        if (currentCell.getPlantList() != null && ThreadLocalRandom.current().nextInt(MouseProperties.CHANCE_TO_EAT_PLANT) == 0) {
            Iterator<Plant> iteratorForPlant = currentCell.getPlantList().iterator();
            while (iteratorForPlant.hasNext() && t.getWeight() <= MouseProperties.MAX_WEIGHT_MOUSE) {
                eatPlant(t);
                iteratorForPlant.remove();
            }
        }
    }

    @Override
    public void bornOrganism(Coordinate coordinate) {
        if (ThreadLocalRandom.current().nextBoolean() && mouseCountIsNotFull(coordinate)) {
            Animal newBreadedAnimal = new Mouse(MouseProperties.MIN_WEIGHT_MOUSE);
            cellInitializer.initializeBreadedAnimalToCell(coordinate, newBreadedAnimal);
        }
    }

    private boolean mouseCountIsNotFull(Coordinate coordinateForCount) {
        int mouseCount = 0;

        for (Herbivore mousesInHerbivore : cellInitializer.island.getCells(coordinateForCount).getHerbivoreList()) {
            if (mousesInHerbivore instanceof Mouse) {
                mouseCount++;
            }
        }

        return mouseCount < MouseProperties.MAX_MOUSE_COUNT;
    }

}
