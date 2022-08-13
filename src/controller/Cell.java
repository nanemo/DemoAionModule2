package controller;

import abstractions.Herbivore;
import abstractions.Predator;
import entity.organism.plants.Plant;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

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

}
