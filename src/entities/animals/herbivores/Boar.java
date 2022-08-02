package entities.animals.herbivores;

import abstractions.CellInitializer;
import abstractions.Herbivore;
import controller.Cell;
import controller.Coordinate;
import controller.Island;
import entities.plants.Plant;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class Boar extends Herbivore {
    private static final int STEP = 2;
    private static final int MAX_BOAR_COUNT = 50;
    private static final int MIN_WEIGHT_BOAR = 500;
    private static final int MAX_WEIGHT_BOAR = 550;
    private static CellInitializer cellInitializer = new CellInitializer();
    private static Coordinate coordinate = new Coordinate();

    public Boar(Double weight) {
        super(weight);
    }

    @Override
    public void eat() {
        Cell currentCell = cellInitializer.findAnimalInCells(this);
        int chooseRandomFood = ThreadLocalRandom.current().nextInt(1, 4);
        if (chooseRandomFood == 1 && currentCell.getPlantList() != null) {
            eatPlant(currentCell);
        } else if (chooseRandomFood == 2 && currentCell.getHerbivoreList() != null) {
            for (Herbivore lookingForCaterpillar : currentCell.getHerbivoreList()) {
                if (lookingForCaterpillar instanceof Caterpillar) {
//                    eatCaterpillar(lookingForCaterpillar);
                }
            }
        }

    }

    private void eatPlant(Cell currentCell) {
        List<Plant> plantList = currentCell.getPlantList();

        if (this.getWeight() <= MIN_WEIGHT_BOAR) { // here we are checking if Boar weight is lower & equals than 500 kq than Boar
            // should eat Plant if there is any plant in this Cell with this coordinates.
            while (plantList != null && this.getWeight() <= MAX_WEIGHT_BOAR && plantList.size() != 0) { // Plant is not null and Boar's weight is not higher 550
                Plant plant = plantList.get(plantList.size() - 1); // we get a last plant from plantList
                Double plantWeight = plant.getWeight() - 1; // Boar is eating plant one by one
                plantList.remove(plantList.size() - 1); // here remove a last object of plant
                this.setWeight(this.getWeight() + 1); // Boar gets weight
                System.out.println(plantList.size());
                System.out.println(this.getWeight());
            }
        }
    }

    // Не готовый метод.
    private void eatCaterpillar(Caterpillar caterpillar) { // Burani ishle, Caterpiller objecti gelmelidir bura ve
        // listin ichinden katerpillerleri yemelidir
//        List<Herbivore> herbivoreList = caterpillar.getHerbivoresList();

//        if (this.getWeight() <= MIN_WEIGHT_BOAR) {
//            while (herbivoreList != null && this.getWeight() <= 550 && herbivoreList.size() != 0) {
//                Herbivore herbivore = herbivoreList.get(herbivoreList.size() - 1);
//                Double plantWeight = herbivore.getWeight() - 1;
//                herbivoreList.remove(herbivoreList.size() - 1);
//                this.setWeight(this.getWeight() + 1);
//                System.out.println(herbivoreList.size());
//                System.out.println(this.getWeight());
//            }
//        }
    }

    private void eatMouse(Cell currentCell) {

    }

    @Override
    public void bread() {

    }

    @Override
    public void die() {
        if (this.getWeight() <= 0) {
//            Cell currentBoarObject = CellInitializer.findAnimalInCells(this);
        }
    }

    public void move() {
        Cell currentBoarObject = cellInitializer.findAnimalInCells(this);
        Coordinate newCoordinates = defineNewDirection(currentBoarObject.getCoordinate(), STEP);
        if (checkBoarCountInThisCell(newCoordinates)) {
            cellInitializer.initializeAnimalToNewCoordinates(newCoordinates, this);
        }
    }

    private boolean checkBoarCountInThisCell(Coordinate coordinate) { // Burda qalmisan
        int boarCount = 0;

        List<Herbivore> herbivoreList = cellInitializer.getCellByCoordinates(coordinate.getCoordinateX(),
                coordinate.getCoordinateY()).getHerbivoreList();

        for (Herbivore boarsInHerbivore : herbivoreList) {
            if (boarsInHerbivore instanceof Boar) {
                boarCount++;
            }
        }
        return boarCount < MAX_BOAR_COUNT;
    }

    private synchronized Coordinate defineNewDirection(Coordinate coordinate, int step) {
        int randomNumForDirection = ThreadLocalRandom.current().nextInt(1, 5);

        if (randomNumForDirection == STRAIGHT) {
            System.out.println("STRAIGHT");
            coordinate.setCoordinateX(defineNewCoordinateStraightDirection(coordinate.getCoordinateX(), step));
            coordinate.setCoordinateY(coordinate.getCoordinateY());
        } else if (randomNumForDirection == BACK) {
            System.out.println("BACK");
            coordinate.setCoordinateX(defineNewCoordinateBackDirection(coordinate.getCoordinateX(), step));
            coordinate.setCoordinateY(coordinate.getCoordinateY());
        } else if (randomNumForDirection == TO_RIGHT) {
            System.out.println("RIGHT");
            coordinate.setCoordinateX(coordinate.getCoordinateX());
            coordinate.setCoordinateY(defineNewCoordinateRightDirection(coordinate.getCoordinateY(), step));
        } else {
            System.out.println("LEFT");
            coordinate.setCoordinateX(coordinate.getCoordinateX());
            coordinate.setCoordinateY(defineNewCoordinateLeftDirection(coordinate.getCoordinateY(), step));
        }
        return coordinate;
    }

    private int defineNewCoordinateLeftDirection(int coordinateY, int step) {
        if (coordinateY == 0) {
            return Island.getCellCoordinateYSize() - 1 - step;
        } else if (coordinateY == 1) {
            return Island.getCellCoordinateYSize() - 1;
        } else {
            return coordinateY - step;
        }
    }

    private int defineNewCoordinateRightDirection(int coordinateY, int step) {
        if (coordinateY == Island.getCellCoordinateYSize() - 1) {
            return step;
        } else if (coordinateY == Island.getCellCoordinateYSize() - 2) {
            return step - 1;
        } else {
            return coordinateY + step;
        }
    }

    private int defineNewCoordinateBackDirection(int coordinateX, int step) {
        if (coordinateX == Island.getCellCoordinateXSize() - 1) {
            return step;
        } else if (coordinateX == Island.getCellCoordinateXSize() - 2) {
            return step - 1;
        } else {
            return coordinateX + step;
        }
    }

    private int defineNewCoordinateStraightDirection(int coordinateX, int step) {
        if (coordinateX == 0) {
            return Island.getCellCoordinateXSize() - 1 - step;
        } else if (coordinateX == 1) {
            return Island.getCellCoordinateXSize() + 1 - step;
        } else {
            return coordinateX - step;
        }
    }

}
