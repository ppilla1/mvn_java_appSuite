package io.explore.controller;

import io.explore.model.Foo;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Log4j2
@RestController
@RequestMapping("/foos")
class FooController {
    private final MeterRegistry registry;
    private final Gauge healthGauge;
    private final Random random;

    FooController(MeterRegistry registry) {
        this.random = new Random();
        this.registry = registry;
        this.healthGauge = Gauge.builder("foo.status",
                        () -> this.health(random.nextInt(0, 2) == 1 ? "UP": "DOWN")? 1 : 0)
                                .register(registry);
    }

    boolean health(String status) {
        return status.equalsIgnoreCase("up") ? true : false;
    }

    int randomInt(int min, int max) {
        return this.random.ints(min, max)
                .findFirst()
                .getAsInt();
    }

    @GetMapping({"/all"})
    public List<Foo> findAll() {
        log.info("Finding all foo's");
        List<Foo> foos = new ArrayList<>();
        foos.add(new Foo(1l));
        foos.add(new Foo(2l));

        DistributionSummary summary = DistributionSummary.builder("foo.findall.summary")
                                        .description("Foo find all distribution summary")
                                        .publishPercentileHistogram()
                                        .serviceLevelObjectives(10)
                                        .minimumExpectedValue(5.0)
                                        .maximumExpectedValue(50.0)
                                        .register(registry);
        int summarySize = randomInt(1, 100);
        log.info("Summmary number ---> {}", summarySize);
        summary.record(summarySize);
        Timer timer = Timer.builder("foo.findall")
                .description("Foo find all latency")
                .register(registry);

        timer.record(2, TimeUnit.SECONDS);
        timer.record(2, TimeUnit.SECONDS);
        timer.record(3, TimeUnit.SECONDS);
        timer.record(4, TimeUnit.SECONDS);
        timer.record(8, TimeUnit.SECONDS);
        timer.record(13, TimeUnit.SECONDS);

        // Printing all registered mirometer's
        registry.getMeters().stream()
                .forEach(mtr -> {
                    log.info("{} -> {}", mtr.getId().getName(), mtr.getId().getType());
                });

        return foos;
    }

    @GetMapping(value = "/{id}")
    public Foo findById(@PathVariable("id") Long id) {
        log.info("Found foo");
        return new Foo(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody Foo resource) {
        log.error("Error while creating foo");
        return -1l;
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @RequestBody Foo resource) {
        log.warn("Foo may or may not be updated");
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        log.error("Error while deleting foo");
    }

}