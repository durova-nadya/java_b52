package ru.stqa.geometry.figures;

public class Triangle {

    private double side1;
    private double side2;
    private double side3;

    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    public static void printArea(Triangle t) {
        String text = String.format("Площадь треугольника со сторонами %.2f, %.2f и %.2f равна %.2f", t.side1, t.side2, t.side3, t.area());
        System.out.println(text);
    }

    public static void printPerimetr(Triangle t) {

        String text = String.format("Периметр треугольника со сторонами %.2f, %.2f и %.2f равен %.2f", t.side1, t.side2, t.side3, t.perimetr());
        System.out.println(text);
    }

    public double area() {
        double semiPerimetr = this.perimetr() / 2;
        double result = Math.sqrt(semiPerimetr * (semiPerimetr - this.side1) * (semiPerimetr - this.side2) * (semiPerimetr - this.side3));
        return Math.round(result*100.0)/100.0;
    }

    public double perimetr() {
        return Math.round((this.side1 + this.side2 + this.side3)*100.0)/100.0;
    }
}
