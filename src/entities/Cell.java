package entities;

import abstractions.Herbivores;
import abstractions.Predators;
import entities.plants.Plant;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Cell {
    private Integer coordinateX;
    private Integer coordinateY;
    private List<Predators> predatorsList;
    private List<Herbivores> herbivoresList;
    private List<Plant> plantList;

    public Cell(Integer coordinateX, Integer coordinateY, List<Predators> predatorsList, List<Herbivores> herbivoresList, List<Plant> plantList) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.predatorsList = predatorsList;
        this.herbivoresList = herbivoresList;
        this.plantList = plantList;
    }

    public Cell(Integer coordinateX, Integer coordinateY){
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public Cell(Integer coordinateX, Integer coordinateY, List<Herbivores> herbivoresList, List<Plant> plantList) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.herbivoresList = herbivoresList;
        this.plantList = plantList;
    }

    public Cell(){

    }

    @Override
    public String toString() {
        return "Cell{" +
               "coordinateX=" + coordinateX +
               ", coordinateY=" + coordinateY +
               ", predatorsList=" + predatorsList +
               ", herbivoresList=" + herbivoresList.size() +
               ", plantList=" + plantList.size() +
               '}';
    }
}
