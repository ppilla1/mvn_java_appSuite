package io.explore;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Log4j2
public class MonoTest
{

    @Test
    public void monoSubscriber()
    {
        String name = "Prashant Pillai";
        Mono<String> nameMono = Mono.just(name).log();

        StepVerifier.create(nameMono)
                .expectNext(name)
                .verifyComplete();
    }

    @Test
    public void monoSubscriberConsumer(){
        String name = "Prashant Pillai";
        Mono<String> monoName = Mono.just(name).log();
        monoName.subscribe(val -> log.info("{}", val));

        StepVerifier.create(monoName)
                .expectNext(name)
                .verifyComplete();
    }

    @Test
    public void monoSubscriberError(){
        String name = "Prashant Pillai";

        Mono<String> nameMono = Mono.just(name).map(val -> {throw new RuntimeException("Testing mono exception");});

        nameMono.subscribe(
                val -> log.info("Received value {}", val),
                err -> log.error("Exception {}\n", err.getMessage(), err));


        StepVerifier.create(nameMono)
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    public void monoSubscriberConsumerComplete() {
        String name = "Prashant Pillai";
        Mono<String> nameMono = Mono.just(name)
                                    .log()
                                    .map(String::toUpperCase);

        nameMono.subscribe(
                val -> log.info("{}", val),
                err -> log.error("{}\n", err.getMessage(), err),
                () -> log.info("Processing completed!!"));

        StepVerifier.create(nameMono)
                .expectNext(name.toUpperCase())
                .verifyComplete();
    }

    @Test
    public void monoSubscriberConsumerSubscription(){
        String name = "Prashant Pillai";
        Mono<String> nameMono = Mono.just(name).log().map(String::toUpperCase);

        nameMono.subscribe(
                val -> log.info("Received {}", val),
                err -> log.error("Exeception happened {}\n", err.getMessage(), err),
                () -> log.info("Processing Completed !!"),
                subscription -> subscription.request(5)
        );

        StepVerifier.create(nameMono)
                .expectNext(name.toUpperCase())
                .verifyComplete();
    }

    @Test
    public void monoDoOnMethods() {
        String name = "Prashant Pillai";
        Mono<Object> nameMono = Mono.just(name).log()
                .map(String::toUpperCase)
                .doOnSubscribe(subscription -> log.info("Subscribed"))
                .doOnRequest(longNum -> log.info("Requested messages [{}]", longNum))
                .doOnNext(val -> log.info("Executing {}", val))
                .doOnSuccess(val -> log.info("doOnSuccess executed for {}", val))
                .flatMap(val -> Mono.empty())
                .doOnNext(val -> log.info("Executing {}", val))
                .doOnSuccess(val -> log.info("doOnSuccess executed for {}", val));

        nameMono.subscribe(
                val -> log.info("Received {}", val),
                err -> log.error("Exeception happened {}\n", err.getMessage(), err),
                () -> log.info("Processing Completed !!"),
                subscription -> subscription.request(5)
        );
    }

    @Test
    public void monoDoOnError(){
        Mono<Object> nameMono = Mono.error(new IllegalArgumentException("Some exception"));

        nameMono
                .doOnError(err -> log.error("Error message {}", err.getMessage(), err))
                ;
        nameMono.log()
                .subscribe(
                val -> log.info("Received {}", val),
                err -> log.error("Exeception happened {}\n", err.getMessage(), err),
                () -> log.info("Processing Completed !!"),
                subscription -> subscription.request(5)
        );
    }

    @Test
    public void monoOnErrorResume(){
        String name = "Prashant Pillai";

        Mono<String> errorMono = Mono.error(new IllegalArgumentException("Illegal Argument exception"))
                .doOnError(err -> log.error("Error happened [{}]", err.getMessage()))
                .onErrorResume(s -> {
                    log.info("Executing onErrorResume");
                    return Mono.just(name);
                })
                .map(s -> ((String)s).toUpperCase())
                .doOnNext(s -> log.info("Received after error resume {}", s))
                .log();

        StepVerifier.create(errorMono)
                .expectNext(name.toUpperCase())
                .verifyComplete();
    }
}
