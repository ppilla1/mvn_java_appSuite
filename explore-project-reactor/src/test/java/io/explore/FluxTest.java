package io.explore;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@Log4j2
public class FluxTest {

    @Test
    public void fluxSubscriber(){
        Flux<String> stringFlux = Flux.just("the", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog").log();

        stringFlux.subscribe(s -> log.info("{}", s));

        StepVerifier.create(stringFlux)
                .expectNext("the", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog")
                .verifyComplete();

        Flux<Integer> numFlux = Flux.range(1, 5)
                                .log();
        numFlux.subscribe( i -> log.info("{}", i));

        StepVerifier.create(numFlux)
                .expectNext(1, 2, 3, 4, 5)
                .verifyComplete();
    }

    @Test
    public void fluxSubscriberNumbersError() {
        Flux<Integer> fluxNum = Flux.range(1, 5).log()
                                .map(i -> {
                                    if (i == 4){
                                        throw new IndexOutOfBoundsException("Index out of bound");
                                    }

                                    return i * 2;
                                });


        fluxNum.subscribe(
                i -> log.info("Processed [{}]", i),
                err -> log.error("Received error {}", err.getMessage(), err),
                () -> log.info("Processing completed"));

        StepVerifier.create(fluxNum)
                .expectNext(2, 4, 6)
                .expectError(IndexOutOfBoundsException.class)
                .verify();
    }


}
