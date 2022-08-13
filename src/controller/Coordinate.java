package controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coordinate {
    private int coordinateX;
    private int coordinateY;

    public Coordinate(){

    }

    public Coordinate(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }


    @Override
    public String toString() {
        return "AnimalsCoordinate{" +
               "coordinateX=" + coordinateX +
               ", coordinateY=" + coordinateY +
               '}';
    }
}
