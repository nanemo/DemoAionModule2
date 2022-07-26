package services;

import entities.Cell;

public class CellInitializer {

    private static final Cell[][] cells = new Cell[100][20];

    public static void cellInitializer(Cell cell) {
        for (Animal herbivores : cell.getHerbivoresList()) {
            int coordinateX = herbivores.getCoordinateX();
            int coordinateY = herbivores.getCoordinateY();
            cells[coordinateX][coordinateY] = cell;
        }
    }

    public static Cell[][] getInitializedCell(){
        return cells;
    }
}