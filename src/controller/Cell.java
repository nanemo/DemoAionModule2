package controller;

import abstractions.Herbivore;
import abstractions.Predator;
import entities.plants.Plant;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Cell {
    private Coordinate coordinate;
    private List<Predator> predatorList;
    private List<Herbivore> herbivoreList;
    private List<Plant> plantList;

    public Cell() {

    }

    @Override
    public String toString() {
        return "Cell{" +
               "animalsCoordinate=" + coordinate +
               ", predatorsList=" + predatorList +
               ", herbivoresList=" + herbivoreList +
               ", plantList=" + plantList +
               '}';
    }
}
