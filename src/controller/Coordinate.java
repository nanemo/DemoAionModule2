package controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coordinate {
    private Integer coordinateX;
    private Integer coordinateY;

    public Coordinate(){

    }

    public Coordinate(Integer coordinateX, Integer coordinateY) {
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
