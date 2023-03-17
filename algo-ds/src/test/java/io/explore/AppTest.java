package io.explore;


import io.explore.dp.Fibonacci;
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
        assertEquals(1, Fibonacci.fib(1, new HashMap<>()));
        assertEquals(1, Fibonacci.fib(2, new HashMap<>()));
        assertEquals(2, Fibonacci.fib(3, new HashMap<>()));
        assertEquals(3, Fibonacci.fib(4, new HashMap<>()));
        assertEquals(5, Fibonacci.fib(5, new HashMap<>()));
    }
}
