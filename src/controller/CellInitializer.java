package controller;

import abstractions.Animal;
import abstractions.Herbivore;
import abstractions.Predator;
import entity.organism.plants.Plant;

import java.util.Iterator;
import java.util.List;

public class CellInitializer {
    public Island island = Island.getInstanceIsland();

    public void primaryCellInitializer(Coordinate coordinate, Cell cell) {
        island.setCells(coordinate, cell);
    }

    public Cell getCellByCoordinates(Coordinate coordinate) {
        return island.getCells(coordinate);
    }

    public <T extends Animal> boolean deleteAnimalFromCells(Coordinate coordinate, T t) { // Method islemir
        if (animalIsHerbivore(t)) {
            Iterator<Herbivore> herbivoreIterator = island.getCells(coordinate).getHerbivoreList().iterator();
            while (herbivoreIterator.hasNext()) {
                if (t.hashCode() == herbivoreIterator.next().hashCode()) {
                    return island.getCells(coordinate).getHerbivoreList().remove(t);
                }
            }
        } else if (!(animalIsHerbivore(t))) {
            Iterator<Predator> predatorIterator = island.getCells(coordinate).getPredatorList().iterator();
            while (predatorIterator.hasNext()) {
                if (t.hashCode() == predatorIterator.next().hashCode()) {
                    return island.getCells(coordinate).getPredatorList().remove(t);
                }
            }
        }
        throw new IllegalArgumentException();
    }

    public List<Plant> getPlantListFromCell(Coordinate coordinate) {
        return island.getCells(coordinate).getPlantList();
    }

    public <T extends Animal> void moveAnimalToNewCoordinate(Coordinate newCoordinate, Coordinate oldCoordinate, T t) {
        if (animalIsHerbivore(t)) {
            island.getCells(newCoordinate).getHerbivoreList().add((Herbivore) t);
            deleteAnimalFromCells(oldCoordinate, t);
        } else if (!(animalIsHerbivore(t))) {
            island.getCells(newCoordinate).getPredatorList().add((Predator) t);
            deleteAnimalFromCells(oldCoordinate, t);
        }
    }

    public <T extends Animal> void initializeBreadedAnimalToCell(Coordinate newCoordinate, T t) {
        if (this.animalIsHerbivore(t)) {
            island.getCells(newCoordinate).getHerbivoreList().add((Herbivore) t);
        } else if (!(this.animalIsHerbivore(t))) {
            island.getCells(newCoordinate).getPredatorList().add((Predator) t);
        }

    }

    private <T extends Animal> boolean animalIsHerbivore(T t) {
        return t instanceof Herbivore;
    }

    public void initializeNewGrowPlantToCell(Coordinate coordinate, Plant newGrowPlant) {
        island.getCells(coordinate).getPlantList().add(newGrowPlant);
    }
}