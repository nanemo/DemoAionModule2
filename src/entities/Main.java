package entities;

import entities.animals.herbivores.Boar;
import services.Animal;

public class Main {
    public static void main(String[] args) {
        Animal mouse = Boar.getInstance();
        System.out.println(mouse.getWeight());
    }
}
