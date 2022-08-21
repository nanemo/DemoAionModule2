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
import property.organismproperty.predatorproperty.BoaProperties;
import property.util.BornOrganism;
import property.util.EatableAnimal;
import property.util.MovableAnimal;

import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Boa extends Predator implements MovableAnimal, EatableAnimal, BornOrganism {

    public Boa(double weight) {
        super(weight);
    }

    private CellInitializer cellInitializer = new CellInitializer();

    @Override
    public <T extends Animal> void move(Coordinate coordinate, T t) {
        Coordinate newCoordinates = defineNewDirection(coordinate, BoaProperties.STEP);
        if (boaCountIsNotFull(newCoordinates)) {
            cellInitializer.moveAnimalToNewCoordinate(newCoordinates, coordinate, t);
        }
    }

    @Override
    public synchronized void eat(Coordinate coordinate) {
        Cell currentCell = cellInitializer.island.getCells(coordinate);
        Iterator<Herbivore> iteratorForHerbivores = currentCell.getHerbivoreList().iterator();
        while (iteratorForHerbivores.hasNext() && this.getWeight() <= BoaProperties.MAX_WEIGHT_BOA) {
            String className = iteratorForHerbivores.next().getClass().getName();
            if (Objects.equals(className, Rabbit.class.getName()) && ThreadLocalRandom.current().nextInt(101) <= BoaProperties.CHANCE_TO_EAT_RABBIT) {
                eatRabbit(this);
                iteratorForHerbivores.remove();
            } else if (Objects.equals(className,Mouse.class.getName()) && ThreadLocalRandom.current().nextInt(101) <= BoaProperties.CHANCE_TO_EAT_MOUSE) {
                eatMouse(this);
                iteratorForHerbivores.remove();
            } else if (Objects.equals(className,Duck.class.getName()) && ThreadLocalRandom.current().nextInt(101) <= BoaProperties.CHANCE_TO_EAT_DUCK) {
                eatDuck(this);
                iteratorForHerbivores.remove();
            } else if (currentCell.getPredatorList() != null){
                Iterator<Predator> iteratorForPredators = currentCell.getPredatorList().iterator();
                while (iteratorForPredators.hasNext() && this.getWeight() <= BoaProperties.MAX_WEIGHT_BOA) {
                    if (Objects.equals(iteratorForPredators.next().getClass().getName(),Fox.class.getName()) && ThreadLocalRandom.current().nextInt(101) <= BoaProperties.CHANCE_TO_EAT_FOX) {
                        eatFox(this);
                        iteratorForPredators.remove();
                    } else {
                        dietAnimal(coordinate);
                    }
                }
            } else {
                dietAnimal(coordinate);
            }
        }
    }

    @Override
    public void bornOrganism(Coordinate coordinate) {
        if (ThreadLocalRandom.current().nextBoolean() && boaCountIsNotFull(coordinate)) {
            Animal newBreadedAnimal = new Boa(BoaProperties.MIN_WEIGHT_BOA);
            cellInitializer.initializeBreadedAnimalToCell(coordinate, newBreadedAnimal);
        }
    }

    private synchronized <T extends Animal> void dietAnimal(Coordinate coordinate) {
        if (weightLoss(this) <= 0){
            cellInitializer.getCellByCoordinates(coordinate).getPredatorList().remove(this);
        }
    }

    private boolean boaCountIsNotFull(Coordinate coordinateForCount) {
        int boaCount = 0;

        for (Predator boasInPredators : cellInitializer.island.getCells(coordinateForCount).getPredatorList()) {
            if (boasInPredators instanceof Boa) {
                boaCount++;
            }
        }

        return boaCount < BoaProperties.MAX_COUNT_BOA;
    }

}
