package main;

import controller.Cell;
import controller.CellInitializer;
import controller.Coordinate;
import controller.Island;
import entity.organism.herbivores.Boar;
import factory.FactoryAnimal;
import property.util.MovableAnimal;

import java.util.concurrent.ThreadLocalRandom;

public class Start {

    public static void main(String[] args) {

        MovableAnimal movableAnimal = FactoryAnimal.getFactoryAnimalInstance().getListAnimalFactory().get(0);


        new Boar(500.00).bornOrganism(new Coordinate(0, 1));

        // isExist ile yoxla animal var ya yox
        CellInitializer cellInitializer = new CellInitializer();
        for (int i = 0; i < Island.getInstanceIsland().getCellCoordinateXLength(); i++) {
            for (int j = 0; j < Island.getInstanceIsland().getCellCoordinateYLength(); j++) {
                Coordinate coordinate = new Coordinate(i, j);
                Island.getInstanceIsland().setCells(coordinate, new Cell());
                for (int k = 0; i < 10; i++) {
                    cellInitializer.initializeBreadedAnimalToCell(coordinate,
                            FactoryAnimal.getFactoryAnimalInstance().getListAnimalFactory().get(ThreadLocalRandom.current().nextInt(16)));
                }
                for (int k = 0; k < 200; k++) {
                    cellInitializer.initializeNewGrowPlantToCell(coordinate,
                            FactoryAnimal.getFactoryAnimalInstance().getListPlantFactory().get(0));
                }
            }
        }

        for (int i = 0; i < 5; i++) {

        }


    }
}
