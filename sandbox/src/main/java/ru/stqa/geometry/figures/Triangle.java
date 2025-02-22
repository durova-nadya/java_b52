package ru.stqa.geometry.figures;

public class Triangle {
    public static void printTriangleArea(double a, double b, double c) {
        String text = String.format("Площадь треугольника со сторонами %f, %f и %f равна %f", a, b, c, triangleArea(a, b, c));
        System.out.println(text);
    }

    public static void printTrianglePerimetr(double a, double b, double c) {

        String text = String.format("Периметр треугольника со сторонами %f, %f и %f равен %f", a, b, c, trianglePerimetr(a, b, c));
        System.out.println(text);
    }

    private static double triangleArea(double a, double b, double c) {
        double semiPerimetr = trianglePerimetr(a, b, c) / 2;
        return Math.sqrt(semiPerimetr * (semiPerimetr - a) * (semiPerimetr - b) * (semiPerimetr - c));
    }

    private static double trianglePerimetr(double a, double b, double c) {
        return a + b + c;
    }
}
