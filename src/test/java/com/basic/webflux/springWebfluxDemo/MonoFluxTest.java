package com.basic.webflux.springWebfluxDemo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


// By these tests, we understand the functional workflow of reactive programming
public class MonoFluxTest {

    //--------------------MONO TESTS--------------------//

    @Test
    public void testMono(){
        // monoString is publisher
        Mono<String> monoString = Mono.just("sample_string").log();

        // subscribe to this publisher
        monoString.subscribe(System.out::println);
    }

    @Test
    public void testMonoOnError(){

        // checking for onError()
        // monoString is publisher

        Mono<?> monoString = Mono.just("sample_string")
                .then(Mono.error(new RuntimeException("Exception occurred")))
                .log();

        // subscribe
        monoString.subscribe(System.out::println,
                (e) -> System.out.println(e.getMessage()));

    }



    //--------------------FLUX TESTS--------------------//

    @Test
    public void testFlux(){
        Flux<String> fluxString = Flux.just("Spring", "Springboot", "hibernate", "microservice")
                .concatWithValues("AWS")
                .log();

        fluxString.subscribe(System.out::println);
    }

    @Test
    public void testFluxOnError(){
        Flux<String> fluxString = Flux.just("Spring", "Springboot", "hibernate", "microservice")
                .concatWithValues("AWS")
                .concatWith(Flux.error(new RuntimeException("Exception occurred in Flux")))
                .concatWithValues("Cloud")
                .log();

        fluxString.subscribe(System.out::println,
                (e) -> System.out.println(e.getMessage()));
    }






}
