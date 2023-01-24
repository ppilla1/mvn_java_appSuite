package io.explore;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Partial Application is a technique to break functions calls
 * taking multiple parameters into functions taking
 * multiple parameters but less than original function
 * and retuning functions taking multiple parameters but less
 * than calling function
 */

@Log4j2
public class PartialApplicationFuncProg {
    Function<Integer, BiFunction<Integer, Integer, Integer>> add = x -> (y, z) -> x + y + z;

    @Test
    public void testTriPartialApplicationFunction() {
        BiFunction<Integer, Integer, Integer> addOne = add.apply(1);
        Integer addTwoAndThree = addOne.apply(2, 3);
        assertTrue(addTwoAndThree == 6);
        log.info("add(1)(2,3) = {}", addTwoAndThree);
    }
}
