package ru.stqa.geometry;

import ru.stqa.geometry.figures.Triangle;

public class Geometry {
    public static void main(String[] args) {
        Triangle.printTrianglePerimetr(3.,4., 5.);
        Triangle.printTrianglePerimetr(21.,43., 55.);
        Triangle.printTrianglePerimetr(2.,1., 4.);
        Triangle.printTriangleArea(3., 4., 5.);
    }

}
