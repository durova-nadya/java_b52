package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

   @Test
    void canCalculateArea() {
       var s = new Triangle(3., 3., 3.);
        double result = s.area();
        Assertions.assertEquals(3.897, result, 0.005);
    }

    @Test
    void canCalculatePerimetr() {
        Assertions.assertEquals(15., new Triangle(5., 5., 5.).perimetr(), 0.05);
    }

    @Test
    void cannotCreateTriangleWithNegativeSideA() {
       try {
           new Triangle( -5.0, 5.0, 5.0);
           Assertions.fail();
       } catch (IllegalArgumentException exception) {
           // OK
       }
    }

    @Test
    void cannotCreateTriangleWithNegativeSideB() {
        try {
            new Triangle( 4.0, -4.0, 4.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // OK
        }
    }

    @Test
    void cannotCreateTriangleWithNegativeSideC() {
        try {
            new Triangle( 7.0, 7.0, -7.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // OK
        }
    }

    @Test
    void cannotCreateTriangleWhenSumTwoSidesNotGreaterThirdAB() {
        try {
            new Triangle( 1.0, 1.0, 3.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // OK
        }
    }

    @Test
    void cannotCreateTriangleWhenSumTwoSidesNotGreaterThirdBC() {
        try {
            new Triangle( 4.0, 1.0, 3.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // OK
        }
    }

    @Test
    void cannotCreateTriangleWhenSumTwoSidesNotGreaterThirdAC() {
        try {
            new Triangle( 4.0, 10.0, 3.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            // OK
        }
    }
}
