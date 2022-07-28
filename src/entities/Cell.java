package entities;

import entities.plants.Plant;
import services.Animal;
import services.Creature;
import services.Herbivores;
import services.Predators;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private List<Creature> predatorsList;
    private List<Creature> herbivoresList;
    private List<Creature> plantList;

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
        herbivoresList.add(herbivores);
    }

    private void addPredators(Animal herbivores) {
        predatorsList.add(herbivores);
    }

    public void addPlants(Plant plants) {
        plantList.add(plants);
    }


    public List<Creature> getPredatorsList() {
        return predatorsList;
    }

    public void setPredatorsList(List<Creature> predatorsList) {
        this.predatorsList = predatorsList;
    }

    public List<Creature> getHerbivoresList() {
        return herbivoresList;
    }

    public void setHerbivoresList(List<Creature> herbivoresList) {
        this.herbivoresList = herbivoresList;
    }

    public List<Creature> getPlantList() {
        return plantList;
    }

    public void setPlantList(List<Creature> plantList) {
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
