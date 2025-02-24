package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

   @Test
    void canCalculateArea() {
       var s = new Triangle(3., 3., 3.);
        double result = s.area();
        Assertions.assertEquals(3.897114317029974, result);
    }

    @Test
    void canCalculatePerimetr(){
        Assertions.assertEquals(15., new Triangle(5., 5., 5.).perimetr());
    }
}
