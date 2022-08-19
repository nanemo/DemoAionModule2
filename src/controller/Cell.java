package controller;

import abstractions.Herbivore;
import abstractions.Predator;
import entity.organism.plants.Plant;

import java.util.ArrayList;
import java.util.List;

/**
 *In this class program create the objects of animal and plant for adding in Lists.
 */

public class Cell {
    private Coordinate coordinate;
    private List<Predator> predatorList;
    private List<Herbivore> herbivoreList;
    private List<Plant> plantList;

    public Cell() {
        coordinate = new Coordinate();
        predatorList = new ArrayList<>();
        herbivoreList = new ArrayList<>();
        plantList = new ArrayList<>();
    }

    public void printPredatorList() {
        predatorList.forEach(s -> System.out.println(s.getClass() + " " + s.getWeight()));
    }

    public void printHerbivoreList() {
        predatorList.forEach(s -> System.out.println(s.getClass() + " " + s.getWeight()));
    }

    public void printPlantList() {
        plantList.forEach(s -> System.out.println(s.getClass() + " " + s.getWeight()));
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public synchronized List<Predator> getPredatorList() {
        return predatorList;
    }

    public synchronized List<Herbivore> getHerbivoreList() {
        return herbivoreList;
    }

    public synchronized List<Plant> getPlantList() {
        return plantList;
    }
}
