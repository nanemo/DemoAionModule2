package controller;

import abstractions.Herbivore;
import abstractions.Predator;
import entity.organism.plants.Plant;

import java.util.ArrayList;
import java.util.Collections;
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
        predatorList = Collections.synchronizedList(new ArrayList<>());
        herbivoreList = Collections.synchronizedList(new ArrayList<>());
        plantList = Collections.synchronizedList(new ArrayList<>());
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
