package controller;

import abstractions.Animal;
import abstractions.Herbivore;
import abstractions.Predator;
import entity.organism.plants.Plant;
import factory.FactoryAnimal;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CellInitializer {

    public Island island = Island.getInstanceIsland();

    public void primaryCellInitializer(Coordinate coordinate, Cell cell) {
        island.setCells(coordinate, cell);
    }

    public Cell getCellByCoordinates(Coordinate coordinate) {
        return island.getCells(coordinate);
    }

    public <T extends Animal> void deleteAnimalFromCells(Coordinate coordinate, T t) {
        if (animalIsHerbivore(t)) {
            Iterator<Herbivore> herbivoreIterator = island.getCells(coordinate).getHerbivoreList().iterator();
            while (herbivoreIterator.hasNext()) {
                if (herbivoreIterator.next().equals(t)) {
                    island.getCells(coordinate).getHerbivoreList().remove(t);
                    break;
                }
            }
        } else if (!(animalIsHerbivore(t))) {
            Iterator<Predator> predatorIterator = island.getCells(coordinate).getPredatorList().iterator();
            while (predatorIterator.hasNext()) {
                if (predatorIterator.next().equals(t)) {
                    island.getCells(coordinate).getPredatorList().remove(t);
                    break;
                }
            }
        }
    }

    public List<Plant> getPlantListFromCell(Coordinate coordinate) {
        return island.getCells(coordinate).getPlantList();
    }

    /** In this method */
    public <T extends Animal> void moveAnimalToNewCoordinate(Coordinate newCoordinate, Coordinate oldCoordinate, T t) {
        if (animalIsHerbivore(t)) {
            island.getCells(newCoordinate).getHerbivoreList().add((Herbivore) t);
            deleteAnimalFromCells(oldCoordinate, t);
        } else if (!(animalIsHerbivore(t))) {
            island.getCells(newCoordinate).getPredatorList().add((Predator) t);
            deleteAnimalFromCells(oldCoordinate, t);
        }
    }

    /** In this method we initialize a new borne animal to cell*/
    public <T extends Animal> void initializeBreadedAnimalToCell(Coordinate newCoordinate, T t) {
        if (this.animalIsHerbivore(t)) {
            island.getCells(newCoordinate).getHerbivoreList().add((Herbivore) t);
        } else if (!(this.animalIsHerbivore(t))) {
            island.getCells(newCoordinate).getPredatorList().add((Predator) t);
        }

    }

    /** In this method we check up that object is Herbivore or not*/
    private <T extends Animal> boolean animalIsHerbivore(T t) {
        return t instanceof Herbivore;
    }

    /** */
    public void initializeNewGrowPlantToCell(Coordinate coordinate, Plant newGrowPlant) {
        island.getCells(coordinate).getPlantList().add(newGrowPlant);
    }

    /** Here we initialize 10 random animal objects and 100 plants into an island.
    */
    public void primaryInitializerOrganismToCell() {
        for (int i = 0; i < Island.getInstanceIsland().getCellCoordinateXLength(); i++) {
            for (int j = 0; j < Island.getInstanceIsland().getCellCoordinateYLength(); j++) {
                Coordinate coordinate = new Coordinate(i, j);
                island.setCells(coordinate, new Cell());
                for (int k = 0; k < 10; k++) {
                    initializeBreadedAnimalToCell(coordinate,
                            FactoryAnimal.getFactoryAnimalInstance().getListAnimalFactory().
                                    get(ThreadLocalRandom.current().nextInt(0,15)));
                }
                for (int k = 0; k < 50; k++) {
                    initializeNewGrowPlantToCell(coordinate,
                            FactoryAnimal.getFactoryAnimalInstance().getListPlantFactory().get(0));
                }
            }
        }
    }
}