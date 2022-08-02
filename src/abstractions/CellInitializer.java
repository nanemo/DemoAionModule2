package abstractions;

import controller.Cell;
import controller.Coordinate;
import controller.Island;

public class CellInitializer {
    private Cell[][] cells = Island.getCELLS();

    public void primaryCellInitializer(Cell cell) {
        cells[cell.getCoordinate().getCoordinateX()][cell.getCoordinate().getCoordinateY()] = cell;
    }

    public Cell getCellByCoordinates(int x, int y) {
        Cell cell = cells[x][y];
        return cell;
    }

    public <T extends Animal> void deleteAnimalFromCells(T t) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (this.checkInstanceOfAnimal(t)) {
                    for (Herbivore value : cells[i][j].getHerbivoreList()) {
                        if (value == t) {
                            cells[i][j].getHerbivoreList().remove(t);
                        }
                    }
                } else if (!(this.checkInstanceOfAnimal(t))) {
                    for (Predator value : cells[i][j].getPredatorList()) {
                        if (value == t) {
                            cells[i][j].getPredatorList().remove(t);
                        }
                    }
                }
            }
        }
    }

    public <T extends Animal> Cell findAnimalInCells(T t) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (this.checkInstanceOfAnimal(t)) {
                    for (Herbivore value : cells[i][j].getHerbivoreList()) {
                        if (value == t) {
                            return cells[i][j];
                        }
                    }
                } else if (!(this.checkInstanceOfAnimal(t))) {
                    for (Predator value : cells[i][j].getPredatorList()) {
                        if (value == t) {
                            return cells[i][j];
                        }
                    }
                }
            }
        }
        throw new IllegalArgumentException();
    }

    public <T extends Animal> void initializeAnimalToNewCoordinates(Coordinate coordinate, T t) {
        if (this.checkInstanceOfAnimal(t)) {
            cells[coordinate.getCoordinateX()][coordinate.getCoordinateY()].getHerbivoreList().add((Herbivore) t);
        } else if (!(this.checkInstanceOfAnimal(t))) {
            cells[coordinate.getCoordinateX()][coordinate.getCoordinateY()].getPredatorList().add((Predator) t);
        }
    }

    private <T extends Animal> boolean checkInstanceOfAnimal(T t) {
        if (t instanceof Herbivore) {
            return true;
        } else {
            return false;
        }
    }
}