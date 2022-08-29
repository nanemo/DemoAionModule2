package entity.organism.predators;

import abstractions.Animal;
import abstractions.Herbivore;
import abstractions.Predator;
import controller.Cell;
import controller.CellInitializer;
import controller.Coordinate;
import entity.organism.herbivores.Duck;
import entity.organism.herbivores.Mouse;
import entity.organism.herbivores.Rabbit;
import property.organismproperty.predatorproperty.EagleProperties;
import property.util.BornOrganism;
import property.util.EatableAnimal;
import property.util.MovableAnimal;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Eagle extends Predator implements MovableAnimal, EatableAnimal, BornOrganism {

    public Eagle(double weight) {
        super(weight);
    }

    private CellInitializer cellInitializer = new CellInitializer();

    @Override
    public <T extends Animal> void move(Coordinate coordinate, T t) {
        Coordinate newCoordinates = defineNewDirection(coordinate, EagleProperties.STEP);
        if (eagleCountIsNotFull(newCoordinates)) {
            cellInitializer.moveAnimalToNewCoordinate(newCoordinates, coordinate, t);
        }
    }

    @Override
    public void eat(Coordinate coordinate) {
        Cell currentCell = cellInitializer.island.getCells(coordinate);
        if (this.getWeight() <= EagleProperties.MAX_WEIGHT_EAGLE && currentCell.getLock().tryLock()) {
            try {
                Iterator<Herbivore> iteratorForHerbivores = currentCell.getHerbivoreList().iterator();
                while (iteratorForHerbivores.hasNext() && this.getWeight() <= EagleProperties.MAX_WEIGHT_EAGLE) {
                    String className = iteratorForHerbivores.next().getClass().getName();
                    if (Objects.equals(className, Rabbit.class.getName()) && ThreadLocalRandom.current().
                            nextInt(101) <= EagleProperties.CHANCE_TO_EAT_RABBIT) {
                        eatRabbit(this);
                        iteratorForHerbivores.remove();
                    } else if (Objects.equals(className, Mouse.class.getName()) && ThreadLocalRandom.current().
                            nextInt(101) <= EagleProperties.CHANCE_TO_EAT_MOUSE) {
                        eatMouse(this);
                        iteratorForHerbivores.remove();
                    } else if (Objects.equals(className, Duck.class.getName()) && ThreadLocalRandom.current().
                            nextInt(101) <= EagleProperties.CHANCE_TO_EAT_DUCK) {
                        eatDuck(this);
                        iteratorForHerbivores.remove();
                    } else {
                        dietAnimal(coordinate, this);
                    }
                }

                if (currentCell.getPredatorList() != null) {
                    List<Predator> predators = currentCell.getPredatorList();
                    for (int i = 0; i < predators.size(); i++){
                        Predator predator = predators.get(i);
                        if (this.getWeight() <= EagleProperties.MAX_WEIGHT_EAGLE &&
                                predator != null && Fox.class.getName().equals(predator.getClass().getName()) &&
                                ThreadLocalRandom.current().nextInt(101) <= EagleProperties.CHANCE_TO_EAT_FOX){
                            eatFox(this);
                            predators.remove(predator);
                        } else {
                            dietAnimal(coordinate, this);
                        }

                    }
                }
            } finally {
                currentCell.getLock().unlock();
            }
        }
    }

    @Override
    public void bornOrganism(Coordinate coordinate) {
        if (ThreadLocalRandom.current().nextBoolean() && eagleCountIsNotFull(coordinate)) {
            Animal newBreadedAnimal = new Eagle(EagleProperties.MIN_WEIGHT_EAGLE);
            cellInitializer.initializeBreadedAnimalToCell(coordinate, newBreadedAnimal);
        }
    }

    private boolean eagleCountIsNotFull(Coordinate coordinateForCount) {
        int eagleCount = 0;

        for (Predator eaglesInPredators : cellInitializer.island.getCells(coordinateForCount).getPredatorList()) {
            if (eaglesInPredators instanceof Eagle) {
                eagleCount++;
            }
        }

        return eagleCount < EagleProperties.MAX_COUNT_EAGLE;
    }

}
