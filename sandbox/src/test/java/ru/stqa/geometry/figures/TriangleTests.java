package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

   @Test
    void canCalculateArea() {
       var s = new Triangle(3.0, 3.0, 3.0);
        double result = s.area();
        Assertions.assertEquals(3.90, result);
    }

    @Test
    void canCalculatePerimetr(){
        Assertions.assertEquals(15.00, new Triangle(5.0, 5.0, 5.0).perimetr());
    }
}
