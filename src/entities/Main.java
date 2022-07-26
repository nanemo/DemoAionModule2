package entities;

import entities.animals.herbivores.Boar;
import entities.animals.predators.Bear;
import entities.plants.Plant;
import services.CellInitializer;

public class Main {

    public static void main(String[] args) {
        Cell cell = new Cell();

        Boar boar = new Boar(1, 2, "Boar1",2, 50.00, 400.0);
        Bear bear = new Bear(1, 2, "Bear1",2, 80.00, 500.0);
        Plant plant = new Plant(1, 2, 10.00);

        Boar boar1 = new Boar(1, 2, "Boar2",2, 50.00, 400.0);
        Bear bear1 = new Bear(1, 2, "Bear2",2, 80.00, 500.0);

        Plant plant1 = new Plant(1, 2, 1.00);
        Plant plant2 = new Plant(1, 2, 1.00);
        Plant plant3 = new Plant(1, 2, 1.00);
        Plant plant4 = new Plant(1, 2, 1.00);
        Plant plant5 = new Plant(1, 2, 1.00);

        cell.addAnimals(bear);
        cell.addAnimals(boar);
        cell.addPlants(plant);

        cell.addAnimals(bear1);
        cell.addAnimals(boar1);
        cell.addPlants(plant1);
        cell.addPlants(plant2);
        cell.addPlants(plant3);
        cell.addPlants(plant4);
        cell.addPlants(plant5);

        CellInitializer.cellInitializer(cell);

        Cell[][] initializer = CellInitializer.getInitializedCell();

        System.out.println(initializer[1][2].toString());

        boar.eat();

    }
}
