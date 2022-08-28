package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FluxAndMonoGeneratorServiceTest {

    FluxAndMonoGeneratorService service = new FluxAndMonoGeneratorService();

    @Test
    void namesFLuxTest() {
        var result = service.namesFlux();

        StepVerifier.create(result).expectNext("Name1", "Name2", "Name3").expectNextCount(0).verifyComplete();
    }

    @Test
    void namesFLuxMapTest() {
        var result = service.namesFluxMap();

        StepVerifier.create(result).expectNext("NAME1", "NAME2", "NAME3").expectNextCount(0).verifyComplete();

    }

    @Test
    void namesFLuxImmutabilityTest() {
        var result = service.namesFluxImmutable();

        StepVerifier.create(result).expectNext("Name1", "Name2", "Name3").expectNextCount(0).verifyComplete();

    }

    @Test
    void namesFLuxFilterTest() {
        var result = service.namesFluxWithLessThanX(3);

        StepVerifier.create(result).expectNext("ONE", "TWO").expectNextCount(0).verifyComplete();

    }

    @Test
    void namesFLuxFilterTest_1() {
        var result = service.namesFluxWithLessThanX(0);

        StepVerifier.create(result).expectNext("DEFAULT").expectNextCount(0).verifyComplete();

    }


    @Test
    void namesFLuxFlatMapTest() {
        var result = service.namesFluxFlatMap();

        StepVerifier.create(result).expectNext("O", "N", "E", "T", "W", "O", "T", "H", "R", "E", "E").expectNextCount(0).verifyComplete();

    }

    @Test
    void namesFLuxTransformTest() {
        var result = service.namesFluxTransform();

        StepVerifier.create(result).expectNext("O", "N", "E", "T", "W", "O", "T", "H", "R", "E", "E").expectNextCount(0).verifyComplete();

    }

    @Test
    void namesFLuxConcatMapTest() {
        var result = service.namesFluxConcatMap();

        StepVerifier.create(result).expectNext("O", "N", "E", "T", "W", "O", "T", "H", "R", "E", "E").expectNextCount(0).verifyComplete();

    }

    @Test
    void namesFLuxFlatMapAsyncTest() {
        var result = service.namesFluxFlatMapAsync();

        StepVerifier.create(result).expectNextCount(11).verifyComplete();

    }

    @Test
    void namesMonoFlatMap() {
        var result = service.namesMonoFlatMap();

        StepVerifier.create(result).expectNext(List.of("N", "a", "m", "e", " ", "M", "o", "n", "o"));
    }

    @Test
    void namesMonoFlatMapMany() {
        var result = service.namesMonoFlatMapMany();

        StepVerifier.create(result).expectNext("N", "a", "m", "e", " ", "M", "o", "n", "o").verifyComplete();
    }

    @Test
    void exploreConcatTest() {
        var result = service.exploreConcat();

        StepVerifier.create(result).expectNext("A", "B", "C", "D", "E", "F").verifyComplete();
    }

    @Test
    void exploreConcatWithTest() {
        var result = service.exploreConcatWith();

        StepVerifier.create(result).expectNext("A", "B").verifyComplete();
    }

    @Test
    void exploreMergeTest() {
        var result = service.exploreMerge();

        StepVerifier.create(result).expectNext("A", "D", "B", "E", "C", "F").verifyComplete();
    }

    @Test
    void exploreMergeSeqTest() {
        var result = service.exploreMergeSeq();

        StepVerifier.create(result).expectNext("A", "B", "C", "D", "E", "F").verifyComplete();
    }

    @Test
    void exploreMergeWithTest() {
        var result = service.exploreMergeWith();

        StepVerifier.create(result).expectNext("A", "B").verifyComplete();
    }



    @Test
    void exploreZipTest() {
        var result = service.exploreZip();

        StepVerifier.create(result).expectNext("AD", "BE", "CF").verifyComplete();
    }

    @Test
    void exploreZip4Test() {
        var result = service.exploreZipWith4();

        StepVerifier.create(result).expectNext("AD14", "BE25", "CF36").verifyComplete();
    }

    @Test
    void exploreZipWithTest() {
        var result = service.exploreConcatWith();

        StepVerifier.create(result).expectNext("A", "B").verifyComplete();
    }
}