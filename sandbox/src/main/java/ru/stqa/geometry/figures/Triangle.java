package ru.stqa.geometry.figures;

public class Triangle {
    public static void printArea(double a, double b, double c) {
        String text = String.format("Площадь треугольника со сторонами %.2f, %.2f и %.2f равна %.2f", a, b, c, Area(a, b, c));
        System.out.println(text);
    }

    public static void printPerimetr(double a, double b, double c) {

        String text = String.format("Периметр треугольника со сторонами %.2f, %.2f и %.2f равен %.2f", a, b, c, Perimetr(a, b, c));
        System.out.println(text);
    }

    public static double Area(double a, double b, double c) {
        double semiPerimetr = Perimetr(a, b, c) / 2;
        return Math.sqrt(semiPerimetr * (semiPerimetr - a) * (semiPerimetr - b) * (semiPerimetr - c));
    }

    public static double Perimetr(double a, double b, double c) {
        return a + b + c;
    }
}
