package abstractions;

import entities.Cell;

public class CellInitializer {
    private final static int sizeX = 100;
    private final static int sizeY = 20;
    private static final Cell[][] cells = new Cell[sizeX][sizeY];

    public static void primaryCellInitializer(Cell cell) {
        cells[cell.getCoordinateX()][cell.getCoordinateY()] = cell;
    }

    public static Cell getCellByCoordinates(Integer x, Integer y) {
        Cell cell = cells[x][y];
        return cell;
    }

    public static void deleteHerbivoresObjectFromCells(Herbivores herbivores){
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                for (Herbivores value : cells[i][j].getHerbivoresList()){
                    if (value.equals(herbivores)){
                        cells[i][j].getHerbivoresList().remove(herbivores);
                    }
                }
            }
        }
    }

    public static void deletePredatorsObjectFromCells(Predators predators){
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                for (Predators value : cells[i][j].getPredatorsList()){
                    if (value.equals(predators)){
                        cells[i][j].getPredatorsList().remove(predators);
                    }
                }
            }
        }
    }

    public static Cell findHerbivoresObjectInCells(Herbivores herbivores) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                for (Herbivores value : cells[i][j].getHerbivoresList()){
                    if (value.equals(herbivores)){
                        return cells[i][j];
                    }
                }
            }
        }
        return new Cell();
    }

    public static Cell findPredatorObjectsInCells(Predators predators) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                for (Predators value : cells[i][j].getPredatorsList()){
                    if (value.equals(predators)){
                        return cells[i][j];
                    }
                }
            }
        }
        return new Cell();
    }

    public static void herbivoresInitializer(Cell cell, Herbivores herbivores){
        cells[cell.getCoordinateX()][cell.getCoordinateY()].getHerbivoresList().add(herbivores);
    }

    public static void predatorsInitializer(Cell cell, Predators predators){
        cells[cell.getCoordinateX()][cell.getCoordinateY()].getPredatorsList().add(predators);
    }

    public static int getCellCoordinateXSize() {
        return cells.length;
    }

    public static int getCellCoordinateYSize() {
        return cells[0].length;
    }
}