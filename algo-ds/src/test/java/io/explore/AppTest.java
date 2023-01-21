package io.explore;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
public class AppTest
{
    @Test
    public void shouldAnswerWithTrue() {
        // Fib(1) = 1, Fib(2) = 1, Fib(3) = 2, Fib(4) = 3, Fib(5) = 5
        assertEquals(1, DynamicPrograming.fib(1, new HashMap<>()));
        assertEquals(1, DynamicPrograming.fib(2, new HashMap<>()));
        assertEquals(2, DynamicPrograming.fib(3, new HashMap<>()));
        assertEquals(3, DynamicPrograming.fib(4, new HashMap<>()));
        assertEquals(5, DynamicPrograming.fib(5, new HashMap<>()));
    }
}
