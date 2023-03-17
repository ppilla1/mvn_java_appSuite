package io.explore.concurrency.executor;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
public class ExecutorFrameworkMagic {

    @Test
    public void plain_thread_test(){
        Thread t1 = new Thread(() -> log.info("Thread name: {}", Thread.currentThread().getName()));
        t1.start();
        t1.run();
    }

    @Test
    public void schedule_executorservice_test() throws InterruptedException {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4);

        IntStream.of(5, 10, 15)
                .mapToObj(i -> i)
                .forEach(i ->  executorService.scheduleAtFixedRate(() -> {
                    log.info("Task {}, Thread {} , {}", i, Thread.currentThread().getName(), LocalDateTime.now());
                }, 0, i, TimeUnit.SECONDS));


        Thread.sleep(60000);
        executorService.shutdownNow();
    }

    @Test
    public void fixed_executorService_test(){


    }
}
