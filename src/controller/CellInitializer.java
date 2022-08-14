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

    public Cell getCellByCoordinates(Coordinate coordinate) {
        return island.getCells(coordinate);
    }

    /** With this method program get coordinate an object for removing from cell*/
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

    /** This method gets new coordinate, current coordinate and its object. It checks at first for Type of Object.
     * After adding new animal in cell and remove the object.*/
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

    /** In this method other methods check that object up for that is Herbivore or Predator object*/
    private <T extends Animal> boolean animalIsHerbivore(T t) {
        return t instanceof Herbivore;
    }

    /** Method get coordinate and plan object for adding new object of plant*/
    public void initializeNewGrowPlantToCell(Coordinate coordinate, Plant newGrowPlant) {
        island.getCells(coordinate).getPlantList().add(newGrowPlant);
    }

    /** Here we initialize 10 random animal objects and 100 plants into an island.*/
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