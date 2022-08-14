package controller;

import abstractions.Herbivore;
import abstractions.Predator;
import entity.organism.plants.Plant;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 *In this class program create the objects of animal and plant for adding in Lists.
 */
@Getter
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
}
