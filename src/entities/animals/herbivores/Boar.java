package entities.animals.herbivores;

import abstractions.CellInitializer;
import abstractions.Herbivores;
import entities.Cell;
import entities.plants.Plant;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class Boar extends Herbivores {
    private static final int step = 2;
    private static final int maxBoarCount = 50;
    private static final int minWeightBoar = 500;

    public Boar(String NAME, Integer SPEED, Double SATIATE, Double weight) {
        super(NAME, SPEED, SATIATE, weight);
    }

    @Override
    public void eat() {
        Cell currentCell = CellInitializer.findHerbivoresObjectInCells(this);
        int chooseRandomFood = ThreadLocalRandom.current().nextInt(1, 4);
        if (chooseRandomFood == 1 && currentCell.getPlantList() != null) {
            eatPlant(currentCell);
        } else if (chooseRandomFood == 2 && currentCell.getHerbivoresList() != null) {
            for (Herbivores lookingForCaterpillar : currentCell.getHerbivoresList()) {
                if (lookingForCaterpillar instanceof Caterpillar) {
                    eatCaterpillar(lookingForCaterpillar);
                }
            }
        }

    }

    private void eatPlant(Cell currentCell) {
        List<Plant> plantList = currentCell.getPlantList();

        if (this.getWeight() <= minWeightBoar) { // here we are checking if Boar weight is lower & equals than 500 kq than Boar
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

    // Не готовый метод.
    private void eatCaterpillar(Caterpillar caterpillar) { // Burani ishle, Caterpiller objecti gelmelidir bura ve
        // listin ichinden katerpillerleri yemelidir
        List<Herbivores> herbivoresList = caterpillar.getHerbivoresList();

        if (this.getWeight() <= minWeightBoar) {
            while (herbivoresList != null && this.getWeight() <= 550 && herbivoresList.size() != 0) {
                Herbivores herbivore = herbivoresList.get(herbivoresList.size() - 1);
                Double plantWeight = herbivore.getWeight() - 1;
                herbivoresList.remove(herbivoresList.size() - 1);
                this.setWeight(this.getWeight() + 1);
                System.out.println(herbivoresList.size());
                System.out.println(this.getWeight());
            }
        }
    }

    private void eatMouse(Cell currentCell) {

    }

    @Override
    public void bread() {

    }

    @Override
    public void die() {
        if (this.getWeight() <= 0) {
            Cell currentBoarObject = CellInitializer.findHerbivoresObjectInCells(this);
        }
    }

    public void move() {
        Cell currentBoarObject = CellInitializer.findHerbivoresObjectInCells(this);
        if (currentBoarObject.getCoordinateX() != null && currentBoarObject.getCoordinateY() != null) {
            Cell newCoordinates = defineNewDirection(currentBoarObject.getCoordinateX(), currentBoarObject.getCoordinateY(), step);
            if (checkBoarCountInThisCell(newCoordinates)) {
                CellInitializer.herbivoresInitializer(newCoordinates, this);
            }
        }
    }

    private boolean checkBoarCountInThisCell(Cell cell) { // Burda qalmisan
        int boarCount = 0;

        List<Herbivores> herbivoresList = cell.getHerbivoresList();

        for (Herbivores boarsInHerbivores : herbivoresList) {
            if (boarsInHerbivores.equals(this)) {
                boarCount++;
            }
        }
        return boarCount < maxBoarCount;
    }

    private Cell defineNewDirection(int coordinateX, int coordinateY, int step) {
        Cell cell;
        int randomNumForDirection = ThreadLocalRandom.current().nextInt(1, 5);

        if (randomNumForDirection == STRAIGHT) {
            System.out.println("STRAIGHT");
            cell = new Cell(defineNewCoordinateStraightDirection(coordinateX, step), coordinateY);
        } else if (randomNumForDirection == BACK) {
            System.out.println("BACK");
            cell = new Cell(defineNewCoordinateBackDirection(coordinateX, step), coordinateY);
        } else if (randomNumForDirection == TO_RIGHT) {
            System.out.println("RIGHT");
            cell = new Cell(coordinateX, defineNewCoordinateRightDirection(coordinateY, step));
        } else {
            System.out.println("LEFT");
            cell = new Cell(coordinateX, defineNewCoordinateLeftDirection(coordinateY, step));
        }
        return cell;
    }

    private int defineNewCoordinateLeftDirection(int coordinateY, int step) {
        if (coordinateY == 0) {
            return CellInitializer.getCellCoordinateYSize() - 1 - step;
        } else if (coordinateY == 1) {
            return CellInitializer.getCellCoordinateYSize() - 1;
        } else {
            return coordinateY - step;
        }
    }

    private int defineNewCoordinateRightDirection(int coordinateY, int step) {
        if (coordinateY == CellInitializer.getCellCoordinateYSize() - 1) {
            return step;
        } else if (coordinateY == CellInitializer.getCellCoordinateYSize() - 2) {
            return step - 1;
        } else {
            return coordinateY + step;
        }
    }

    private int defineNewCoordinateBackDirection(int coordinateX, int step) {
        if (coordinateX == CellInitializer.getCellCoordinateXSize() - 1) {
            return step;
        } else if (coordinateX == CellInitializer.getCellCoordinateXSize() - 2) {
            return step - 1;
        } else {
            return coordinateX + step;
        }
    }

    private int defineNewCoordinateStraightDirection(int coordinateX, int step) {
        if (coordinateX == 0) {
            return CellInitializer.getCellCoordinateXSize() - 1 - step;
        } else if (coordinateX == 1) {
            return CellInitializer.getCellCoordinateXSize() + 1 - step;
        } else {
            return coordinateX - step;
        }
    }

}
