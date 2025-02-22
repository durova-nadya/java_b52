package ru.stqa.geometry;

import ru.stqa.geometry.figures.Triangle;

public class Geometry {
    public static void main(String[] args) {
        Triangle.printPerimetr(3.,4., 5.);
        Triangle.printPerimetr(21.,43., 55.);
        Triangle.printPerimetr(2.,1., 4.);
        Triangle.printArea(3., 3., 3.);
    }

}
