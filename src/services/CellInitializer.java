package services;

import entities.Cell;

import java.util.List;

public class CellInitializer {

    private static final Cell[][] cells = new Cell[100][20];

    public static void cellInitializer(Cell cell) {
        for (Creature herbivores : cell.getHerbivoresList()) {
            int coordinateX = herbivores.getCoordinateX();
            int coordinateY = herbivores.getCoordinateY();
            cells[coordinateX][coordinateY] = cell;
        }
    }

    public static List<Creature> getInitializedCell(Creature creature, int x, int y){

        if (creature instanceof Herbivores) {
            List<Creature> herbivoresList = cells[x][y].getHerbivoresList();
            return herbivoresList;
        } else if (creature instanceof Predators){
            List<Creature> predatorsList = cells[x][y].getPredatorsList();
            return predatorsList;
        } else {
            List<Creature> plantList = cells[x][y].getPlantList();
            return plantList;
        }
    }

    public static int getCellCoordinateXSize() {
        return cells.length;
    }

    public static int getCellCoordinateYSize() {
        return cells[0].length;
    }
}