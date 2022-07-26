package entities;

import entities.plants.Plant;
import services.Animal;
import services.Herbivores;
import services.Predators;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Cell {
    private List<Predators> predatorsList;
    private List<Animal> herbivoresList;
    private List<Plant> plantList;

    public Cell() {
        predatorsList = new ArrayList<>();
        herbivoresList = new ArrayList<>();
        plantList = new ArrayList<>();
    }

    public void addAnimals(Animal animalList) {
        if (animalList instanceof Herbivores) {
            addHerbivores(animalList);
        }
        if (animalList instanceof Predators) {
            addPredators(animalList);
        }
    }

    private void addHerbivores(Animal herbivores) {
        herbivoresList.add((Animal) herbivores);
    }

    private void addPredators(Animal herbivores) {
        predatorsList.add((Predators) herbivores);
    }

    public void addPlants(Plant plants) {
        plantList.add((Plant) plants);
    }


    public List<Predators> getPredatorsList() {
        return predatorsList;
    }

    public void setPredatorsList(List<Predators> predatorsList) {
        this.predatorsList = predatorsList;
    }

    public List<Animal> getHerbivoresList() {
        return herbivoresList;
    }

    public void setHerbivoresList(List<Animal> herbivoresList) {
        this.herbivoresList = herbivoresList;
    }

    public List<Plant> getPlantList() {
        return plantList;
    }

    public void setPlantList(List<Plant> plantList) {
        this.plantList = plantList;
    }

    @Override
    public String toString() {
        return "Cell{" +
               "predatorsList=" + predatorsList.toString() +
               ", herbivoresList=" + herbivoresList.toString() +
               ", plantList=" + plantList.toString() +
               '}';
    }
}
