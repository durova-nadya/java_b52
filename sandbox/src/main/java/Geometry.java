public class Geometry {
    public static void main(String[] args) {
        printTrianglePerimetr(3.,4., 5.);
        printTrianglePerimetr(21.,43., 55.);
        printTrianglePerimetr(2.,1., 4.);
        printTriangleArea(3., 4., 5.);
    }

    private static void printTriangleArea(double a, double b, double c) {
        System.out.println("Площадь треугольника со сторонами "
                + a + ',' + b + ',' + c + " равна " + triangleArea(a, b, c));
    }

    static void printTrianglePerimetr(double a, double b, double c) {
        System.out.println("Периметр треугольника со сторонами "
                + a + ',' + b + ',' + c + " равен " + trianglePerimetr(a, b, c));
    }

    private static double triangleArea(double a, double b, double c) {
        var poluPerimetr = trianglePerimetr(a, b, c)/2;
        return poluPerimetr * (poluPerimetr - a) * (poluPerimetr - b) * (poluPerimetr - c);
    }

    private static double trianglePerimetr(double a, double b, double c) {
        return a + b + c;
    }

}
