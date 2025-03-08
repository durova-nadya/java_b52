package ru.stqa.geometry.figures;

import java.util.Objects;

public record Triangle(double a, double b, double c) {

    public Triangle {
        if (a < 0 || b < 0 || c < 0) {
            throw new IllegalArgumentException("Сторона треугольника не может быть отрицательной");
        }
        if (a + b <= c || b + c <= a || a + c <= b) {
            throw new IllegalArgumentException("Сумма двух сторон не может быть меньше третьей стороны");
        }
    }


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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return (Double.compare(this.a, triangle.a) == 0 && Double.compare(this.b, triangle.b) == 0 && Double.compare(this.c, triangle.c) == 0)
                || (Double.compare(this.b, triangle.a) == 0 && Double.compare(this.a, triangle.b) == 0 && Double.compare(this.c, triangle.c) == 0)
                || (Double.compare(this.c, triangle.a) == 0 && Double.compare(this.b, triangle.b) == 0 && Double.compare(this.a, triangle.c) == 0)
                || (Double.compare(this.a, triangle.a) == 0 && Double.compare(this.c, triangle.b) == 0 && Double.compare(this.b, triangle.c) == 0)
                || (Double.compare(this.c, triangle.a) == 0 && Double.compare(this.a, triangle.b) == 0 && Double.compare(this.b, triangle.c) == 0)
                || (Double.compare(this.b, triangle.a) == 0 && Double.compare(this.c, triangle.b) == 0 && Double.compare(this.a, triangle.c) == 0);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }
}
