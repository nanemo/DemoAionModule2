package entities;

import abstractions.Herbivore;
import entities.plants.Plant;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//        Cell cell = new Cell(1,2, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
//        CellInitializer.primaryCellInitializer(cell);

        List<Herbivore> herbivoreList = new ArrayList<>();
//        Herbivore herbivore = new Boar("Boar",2,400.00,  50.00);
//        herbivoreList.add(herbivore);

        Plant plant = new Plant(1.00);
        Plant plant1 = new Plant(1.00);
        Plant plant2 = new Plant(1.00);
        Plant plant3 = new Plant(1.00);
        Plant plant4 = new Plant(1.00);
        Plant plant5 = new Plant(1.00);
        Plant plant6 = new Plant(1.00);

        List<Plant> plantList = new ArrayList<>();
        plantList.add(plant);
        plantList.add(plant1);
        plantList.add(plant2);
        plantList.add(plant3);
        plantList.add(plant4);
        plantList.add(plant5);
        plantList.add(plant6);

    }
}
