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

    @Test
    void testEquality() {
       var t1 = new Triangle(3.0, 4.0, 5.0);
       var t2 = new Triangle(3.0, 4.0, 5.0);
       Assertions.assertEquals(t1, t2);
    }

    @Test
    void testEquality2() {
        var t1 = new Triangle(3.0, 4.0, 5.0);
        var t2 = new Triangle(4.0, 3.0, 5.0);
        Assertions.assertEquals(t1, t2);
    }

    @Test
    void testEquality3() {
        var t1 = new Triangle(3.0, 4.0, 5.0);
        var t2 = new Triangle(5.0, 3.0, 4.0);
        Assertions.assertEquals(t1, t2);
    }

    @Test
    void testEquality4() {
        var t1 = new Triangle(3.0, 4.0, 5.0);
        var t2 = new Triangle(4.0, 5.0, 3.0);
        Assertions.assertEquals(t1, t2);
    }

    @Test
    void testEquality5() {
        var t1 = new Triangle(3.0, 4.0, 5.0);
        var t2 = new Triangle(3.0, 5.0, 4.0);
        Assertions.assertEquals(t1, t2);
    }

    @Test
    void testEquality6() {
        var t1 = new Triangle(3.0, 4.0, 5.0);
        var t2 = new Triangle(5.0, 4.0, 3.0);
        Assertions.assertEquals(t1, t2);
    }

}
