package io.explore;

import lombok.extern.log4j.Log4j2;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Log4j2
public class SchedulerUsingThread {

    Lock lock = new ReentrantLock(true);
    Executor executor = Executors.newFixedThreadPool(2);
}
