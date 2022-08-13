package controller;

public class Island {
    private static final int sizeX = 100;
    private final int sizeY = 20;
    private final Cell[][] cells = new Cell[sizeX][sizeY];
    private static Island island;

    private Island() {

    }

    public static Island getInstanceIsland() {
        if (island == null) {
            island = new Island();
        }
        return island;
    }

    public int getCellCoordinateXLength() {
        return cells.length;
    }

    public int getCellCoordinateYLength() {
        return cells[0].length;
    }

    public Cell getCells(Coordinate coordinate) {
        if (!(cellIsNotNull(coordinate))) {
            cells[coordinate.getCoordinateX()][coordinate.getCoordinateY()] = new Cell();
        }
        return cells[coordinate.getCoordinateX()][coordinate.getCoordinateY()];
    }

    public void setCells(Coordinate coordinate, Cell cell) {
        cells[coordinate.getCoordinateX()][coordinate.getCoordinateY()] = cell;
    }

    private boolean cellIsNotNull(Coordinate coordinate) {
        return cells[coordinate.getCoordinateX()][coordinate.getCoordinateY()] != null;
    }


}
