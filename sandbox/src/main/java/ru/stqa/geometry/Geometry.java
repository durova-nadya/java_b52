package ru.stqa.geometry;

import ru.stqa.geometry.figures.Triangle;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Geometry {
    public static void main(String[] args) {
        Supplier<Triangle> randomTriangle = () -> new Triangle(new Random().nextDouble(100.0), new Random().nextDouble(100.0), new Random().nextDouble(100.0));
        var triangles = Stream.generate(randomTriangle).limit(5);
        triangles.peek(Triangle::printPerimetr).forEach(Triangle::printArea);
    }
}
