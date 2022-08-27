package entity.organism.herbivores;

import abstractions.Animal;
import abstractions.Herbivore;
import controller.Cell;
import controller.CellInitializer;
import controller.Coordinate;
import property.organismproperty.herbivoreproperty.MouseProperties;
import property.util.BornOrganism;
import property.util.EatableAnimal;
import property.util.MovableAnimal;

import java.util.Iterator;
import java.util.Objects;
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
    public synchronized void eat(Coordinate coordinate) {
        Cell currentCell = cellInitializer.island.getCells(coordinate);
        Iterator<Herbivore> iteratorForHerbivores = currentCell.getHerbivoreList().iterator();
        while (iteratorForHerbivores.hasNext() && this.getWeight() <= MouseProperties.MAX_WEIGHT_MOUSE) {
            if (Objects.equals(iteratorForHerbivores.next().getClass().getName(), Caterpillar.class.getName()) && ThreadLocalRandom.current().nextInt(101) <= MouseProperties.CHANCE_TO_EAT_CATERPILLAR) {
                eatCaterpillar(this);
                iteratorForHerbivores.remove();
            } else if (currentCell.getPlantList() != null) {
                while (!(currentCell.getPlantList().isEmpty()) && this.getWeight() <= MouseProperties.MAX_WEIGHT_MOUSE) {
                    eatPlant(this);
                    currentCell.getPlantList().remove(0);
                }
            } else {
                dietAnimal(coordinate, this);
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

    /** This method is same in other animal classes.
     * We can take it to interface and do that method default for all implement classes.
     * But for now we configured the island_model with threads in a pool. I don't want to take this method because
     * might be we will lose control on ThreadTaskManager. But further i will take a look on this application and
     * finish it.*/
    private <T extends Animal> void dietAnimal(Coordinate coordinate, T t) {
        if (weightLoss(t) <= 0) {
            cellInitializer.getCellByCoordinates(coordinate).getHerbivoreList().remove(t);
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
