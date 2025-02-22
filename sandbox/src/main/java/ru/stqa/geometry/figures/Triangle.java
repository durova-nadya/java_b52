package ru.stqa.geometry.figures;

public record Triangle(double a, double b, double c) {


    public static void printArea(Triangle t) {
        String text = String.format("Площадь треугольника со сторонами %.2f, %.2f и %.2f равна %.2f", t.a, t.b, t.c, t.area());
        System.out.println(text);
    }

    public static void printPerimetr(Triangle t) {

        String text = String.format("Периметр треугольника со сторонами %.2f, %.2f и %.2f равен %.2f", t.a, t.b, t.c, t.perimetr());
        System.out.println(text);
    }

    public double area() {
        double semiPerimetr = this.perimetr() / 2;
        double result = Math.sqrt(semiPerimetr * (semiPerimetr - this.a) * (semiPerimetr - this.b) * (semiPerimetr - this.c));
        return Math.round(result*100.0)/100.0;
    }

    public double perimetr() {
        return Math.round((this.a + this.b + this.c)*100.0)/100.0;
    }
}
