package ru.stqa.geometry;

import ru.stqa.geometry.figures.Triangle;

public class Geometry {
    public static void main(String[] args) {
        Triangle.printPerimetr(new Triangle(3.,4., 5.));
        Triangle.printPerimetr(new Triangle(5.,5., 5.));
        Triangle.printPerimetr(new Triangle(2.,1., 4.));
        Triangle.printArea(new Triangle(3., 3., 3.));
    }

}
