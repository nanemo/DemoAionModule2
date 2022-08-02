package controller;

public class Island {
    private final static int SIZE_X = 100;
    private final static int SIZE_Y = 20;
    private static final Cell[][] CELLS = new Cell[SIZE_X][SIZE_Y];

    public static int getCellCoordinateXSize() {
        return CELLS.length;
    }

    public static int getCellCoordinateYSize() {
        return CELLS[0].length;
    }

    public static int getSizeX() {
        return SIZE_X;
    }

    public static int getSizeY() {
        return SIZE_Y;
    }

    public static Cell[][] getCELLS() {
        return CELLS;
    }
}
