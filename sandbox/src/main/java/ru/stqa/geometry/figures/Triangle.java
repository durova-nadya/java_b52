package ru.stqa.geometry.figures;

public record Triangle(double a, double b, double c) {


    public static void printArea(Triangle t) {
        String text = String.format("Площадь треугольника со сторонами %f, %f и %f равна %f", t.a, t.b, t.c, t.area());
        System.out.println(text);
    }

    public static void printPerimetr(Triangle t) {

        String text = String.format("Периметр треугольника со сторонами %f, %f и %f равен %f", t.a, t.b, t.c, t.perimetr());
        System.out.println(text);
    }

    public double area() {
        double semiPerimetr = this.perimetr() / 2;
        return Math.sqrt(semiPerimetr * (semiPerimetr - this.a) * (semiPerimetr - this.b) * (semiPerimetr - this.c));
    }

    public double perimetr() {
        return this.a + this.b + this.c;
    }
}
