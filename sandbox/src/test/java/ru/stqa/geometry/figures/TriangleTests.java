package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

   @Test
    void canCalculateArea() {
        double result = Math.round(Triangle.Area(3.0, 3.0, 3.0)* 100.0)/100.0;
        Assertions.assertEquals(3.90, result);
    }

    @Test
    void canCalculatePerimetr(){
        double result = Math.round(Triangle.Perimetr(5.0, 5.0, 5.0)*100.0)/100.0;
        Assertions.assertEquals(15.00, result);
    }
}
