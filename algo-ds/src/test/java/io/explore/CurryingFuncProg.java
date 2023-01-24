package io.explore;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.util.function.Function;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Currying is a technique to break functions calls
 * taking multiple parameters into functions taking
 * single parameter and retuning functions taking
 * single parameter
 */
@Log4j2
public class CurryingFuncProg {
    Function<Integer, Function<Integer, Function<Integer, Integer>>> add = x -> y -> z -> x + y + z;

    @Test
    public void testTriCurryingFunction() {
        Function<Integer, Function<Integer, Integer>> addOne = add.apply(1);
        Function<Integer, Integer> addTwo = addOne.apply(2);
        Integer addThree = addTwo.apply(3);

        assertTrue(addThree == 6);
        log.info("add(1)(2)(3) = {}", addThree);
    }
}
