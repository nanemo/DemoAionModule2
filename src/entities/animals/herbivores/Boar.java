package entities.animals.herbivores;

import entities.plants.Plant;
import services.*;

import java.util.List;

public class Boar extends Herbivores {
    public static final int step = 2;
    public static final int maxBoarCount = 50;
    private Boar service = this;

    public Boar(int coordinateX, int coordinateY, String NAME, Integer SPEED, Double SATIATE, Double weight) {
        super(coordinateX, coordinateY, NAME, SPEED, SATIATE, weight);
    }

    public void eat(Animal animal, int coordinateX, int coordinateY) {
        coordinateX = service.getCoordinateX();
        coordinateY = service.getCoordinateY();

        List<Creature> plantList = CellInitializer.getInitializedCell(animal, coordinateX, coordinateY);

        if (this.getWeight() <= 500) { // here we are checking if Boar weight is lower & equals than 500 kq than Boar
            // should eat Plant if there is any plant in this Cell with this coordinates.
            while (plantList != null && this.getWeight() <= 550 && plantList.size() != 0) { // Plant is not null and Boar's weight is not higher 550
                Plant plant = (Plant) plantList.get(plantList.size() - 1); // we get a last plant from plantList
                Double plantWeight = plant.getWeight() - 1; // Boar is eating plant one by one
                plantList.remove(plantList.size() - 1); // here remove a last object of plant
                this.setWeight(this.getWeight() + 1); // Boar gets weight
                System.out.println(plantList.size());
                System.out.println(this.getWeight());

            }
        }
    }

    public void toStarve() {

    }

    public void forLearnType(Animal animalList) {
        if (animalList instanceof Herbivores) {
        }
        if (animalList instanceof Predators) {
        }
    }

    public void move() {

    }

    @Override
    public void moveDirection(Animal animal, int direction) {
        int boarCount = 0;
        List<Creature> initializedCell = CellInitializer.getInitializedCell(animal, this.getCoordinateX(), this.getCoordinateY());

        for (Creature boar : initializedCell) {
            if (boar instanceof Boar) {
                boarCount++;
            }
        }

        if (boarCount <= maxBoarCount) {

            int coordinateY = animal.getCoordinateY();
            int coordinateX = animal.getCoordinateX();

            if (direction == STRAIGHT) {
                if (coordinateX == 0) {
                    animal.setCoordinateX(CellInitializer.getCellCoordinateXSize()-2);
                } else if (coordinateX == 1) {
                    animal.setCoordinateX(99);
                } else {
                    animal.setCoordinateX(coordinateX - step);
                }
            } else if (direction == BACK) {

            } else if (direction == TO_RIGHT) {

            } else if (direction == TO_LEFT) {

            } else {

            }
        }

    }

    @Override
    public void bread() {

    }

    @Override
    public void die() {

    }

}
