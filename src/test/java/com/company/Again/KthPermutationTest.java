package com.company.Again;
import org.junit.Test;

import java.lang.String;
import static org.junit.Assert.*;

public class KthPermutationTest {
    @Test
    public void testConcatenate() {
        KthPermutation myUnit = new KthPermutation();

        assertEquals("123", myUnit.solution(3, 1));
        assertEquals("132", myUnit.solution(3, 2));
        //assertEquals("123", myUnit.solution(3, 1));
        //assertEquals("123", myUnit.solution(3, 1));

    }

}