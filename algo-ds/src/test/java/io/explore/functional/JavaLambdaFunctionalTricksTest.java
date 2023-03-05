package io.explore.functional;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Log4j2
public class JavaLambdaFunctionalTricksTest {

    interface NonFuncToFuncInterface {
        static NonFuncInterface execute() {
            List<String> mems = Arrays.asList(
                    "We cannot solve problems with the kind of thinking we employed when we came up with them. — Albert Einstein",
                    "Learn as if you will live forever, live like you will die tomorrow. — Mahatma Gandhi",
                    "Stay away from those people who try to disparage your ambitions. Small minds will always do that, but great minds will give you a feeling that you can become great too. — Mark Twain",
                    "When you give joy to other people, you get more joy in return. You should give a good thought to happiness that you can give out. — Eleanor Roosevelt",
                    "When you change your thoughts, remember to also change your world. —Norman Vincent Peale",
                    "It is only when we take chances, when our lives improve. The initial and the most difficult risk that we need to take is to become honest. —Walter Anderson",
                    "Nature has given us all the pieces required to achieve exceptional wellness and health, but has left it to us to put these pieces together. —Diane McLaren"
            );

            return (i) -> {
                Random random = new Random();
                int id = random.nextInt(mems.size());

                if (i == 0) {
                    return mems.get(id);
                }

                return id;
            };
        }
    }

    @FunctionalInterface
    interface NonFuncInterface {

        Object get(int index);

        default String randomMsg(){
            return (String)get(0);
        }

        default Integer randomInt(){
            return (Integer) get(1);
        }
    }

    @Test
    void test_dynamic_functionalInterface_trick() {
        String msg = NonFuncToFuncInterface.execute().randomMsg();
        int index = NonFuncToFuncInterface.execute().randomInt();

        log.info("\n{} - {}", index, msg);
    }
}
