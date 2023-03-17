package io.explore.concurrency.lock;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;


@Log4j2
public class ProducerConsumerTest {
    Thread thread1 = new Thread(() -> log.info(Thread.currentThread().getName()));

    @Test
    public void testThread() {
        thread1.start();
        //thread1.run();
    }
}
