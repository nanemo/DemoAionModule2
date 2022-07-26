package entities.animals.herbivores;

import entities.Cell;
import entities.plants.Plant;
import services.Animal;
import services.CellInitializer;
import services.Herbivores;

import java.util.List;

public class Boar extends Herbivores {
    private Boar service = this;

    public Boar(int coordinateX, int coordinateY, String NAME, Integer SPEED, Double SATIATE, Double weight) {
        super(coordinateX, coordinateY, NAME, SPEED, SATIATE, weight);
    }

    public void eat() {
        int coordinateX = service.getCoordinateX();
        int coordinateY = service.getCoordinateY();

        Cell[][] initializedCell = CellInitializer.getInitializedCell();

        List<Plant> plantList = initializedCell[coordinateX][coordinateY].getPlantList();

        if (this.getWeight() <= 500) { // here we are checking if Boar weight is lower & equals than 500 kq than Boar
            // should eat Plant if there is any plant in this Cell with this coordinates.
            while (plantList != null && this.getWeight() <= 550 && plantList.size() != 0) { // Plant is not null and Boar's weight is not higher 550
                Plant plant = plantList.get(plantList.size() - 1); // we get a last plant from plantList
                Double plantWeight = plant.getWeight() - 1; // Boar is eating plant one by one
                plantList.remove(plantList.size() - 1); // here remove a last object of plant
                this.setWeight(this.getWeight() + 1); // Boar gets weight
                System.out.println(plantList.size());
                System.out.println(this.getWeight());

            }
        }
    }

    public void toStarve(){

    }

    @Override
    public void move() {

    }

    @Override
    public void moveDirection() {

    }

    @Override
    public void bread() {

    }

    @Override
    public void die() {

    }

}
