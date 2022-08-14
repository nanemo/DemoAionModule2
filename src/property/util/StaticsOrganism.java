package property.util;

import controller.Cell;
import controller.CellInitializer;

/** With help this class client can get statistics of Organisms.*/
public class StaticsOrganism {
    private static CellInitializer cellInitializer = new CellInitializer();
    private static Cell[][] cells = cellInitializer.island.getCELLS();

    private StaticsOrganism() {
    }

    public static void staticsForCountAnimalsAndPlants() {
        int resultHerb = 0;
        int resultPred = 0;
        int resultPlant = 0;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                System.out.printf("In coordinate (%d; %d): \n", i, j);
                System.out.print("Herbivores count is - " + cells[i][j].getHerbivoreList().size());
                resultHerb += cells[i][j].getHerbivoreList().size();
                System.out.print(", Predators count is - " + cells[i][j].getPredatorList().size());
                resultPred += cells[i][j].getPredatorList().size();
                System.out.println(", Plant count is - " + cells[i][j].getPlantList().size());
                resultPlant += cells[i][j].getPlantList().size();
            }
        }
        System.out.println("Herbivores " + resultHerb);
        System.out.println("Predators " + resultPred);
        System.out.println("Plants " + resultPlant);
    }

    public static void staticsAboutWeightAnimals() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                System.out.printf("In coordinate (%d; %d): \n", i, j);
                cells[i][j].getHerbivoreList().forEach(w -> System.out.println(w.getClass().toString() + "'s weight is "
                                                                               + (Math.round(w.getWeight() * 100) / 100.0)));

                cells[i][j].getPredatorList().forEach(w -> System.out.println(w.getClass().toString() + "'s weight is "
                                                                              + (Math.round(w.getWeight() * 100) / 100.0)));

            }
        }

    }
}
