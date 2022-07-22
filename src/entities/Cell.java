package entities;

import services.Animal;
import services.Plants;

import java.util.List;

public class Cell {
    private final int coordinateX;
    private final int coordinateY;
    private List<Animal> animal;
    private List<Plants> plants;


    public Cell(int x, int y, List<Animal> animal, List<Plants> plants) {
        this.coordinateX = x;
        this.coordinateY = y;
        this.animal = animal;
        this.plants = plants;
    }
}
