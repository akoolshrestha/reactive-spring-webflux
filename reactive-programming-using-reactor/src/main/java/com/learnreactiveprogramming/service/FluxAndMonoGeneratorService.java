package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class FluxAndMonoGeneratorService {

    public Flux<String> namesFlux() {
        return Flux.fromIterable(List.of("Name1", "Name2", "Name3")).log();
    }

    public Flux<String> namesFluxMap() {
        return Flux.fromIterable(List.of("Name1", "Name2", "Name3"))
                .map(String::toUpperCase);
    }

    private Flux<String> breakWord(String name) {
        return Flux.fromArray(name.split(""));
    }

    public Flux<String> namesFluxFlatMap() {
        return Flux.fromIterable(List.of("one", "two", "three")).map(String::toUpperCase)

                .flatMap(this::breakWord).log();
    }

    public Flux<String> namesFluxTransform() {

        Function<Flux<String>, Flux<String>> transformMap = name -> name.map(String::toUpperCase);

        return Flux.fromIterable(List.of("one", "two", "three")).transform(transformMap)
                .flatMap(this::breakWord).log();
    }

    public Flux<String> namesFluxFlatMapAsync() {
        return Flux.fromIterable(List.of("one", "two", "three")).map(String::toUpperCase)

                .flatMap(this::breakWordWithDelay).log();
    }

    public Flux<String> namesFluxConcatMap() {
        return Flux.fromIterable(List.of("one", "two", "three")).map(String::toUpperCase)

                .concatMap(this::breakWordWithDelay).log();
    }

    private Flux<String> breakWordWithDelay(String name) {
        var delay = new Random().nextInt(1000);
        return Flux.fromArray(name.split("")).delayElements(Duration.ofMillis(delay));
    }

    public Flux<String> namesFluxWithLessThanX(int length) {

        var defaultFlux = Flux.just("DEFAULT");

        return Flux.fromIterable(List.of("one", "two", "three")).map(String::toUpperCase)
                .filter(s -> s.length() <= length).switchIfEmpty(defaultFlux).log();
    }

    public Flux<String> namesFluxImmutable() {
        var flux = Flux.fromIterable(List.of("Name1", "Name2", "Name3"));
        flux.map(String::toUpperCase);
        return flux;
    }

    public Mono<String> namesMono() {
        return Mono.just("Name Mono").log();
    }

    public Mono<List<String>> namesMonoFlatMap() {
        return Mono.just("Name Mono").flatMap(this::breakWordMono).log();
    }


    public Flux<String> namesMonoFlatMapMany() {
        return Mono.just("Name Mono").flatMapMany(this::breakWord).log();
    }

    private Mono<List<String>> breakWordMono(String s) {
        return Mono.just(List.of(s.split("")));
    }


    public Flux<String> exploreConcat(){
        var flux1 = Flux.just("A", "B", "C");
        var flux2 = Flux.just("D", "E", "F");

        return Flux.concat(flux1, flux2);
    }


    public Flux<String> exploreConcatWith(){
        var mono1 = Mono.just("A");
        var mono2 = Mono.just("B");

        return mono1.concatWith(mono2);
    }

    public Flux<String> exploreMerge(){
        var flux1 = Flux.just("A", "B", "C").delayElements(Duration.ofMillis(100));
        var flux2 = Flux.just("D", "E", "F").delayElements(Duration.ofMillis(150));

        return Flux.merge(flux1, flux2).log();
    }

    public Flux<String> exploreMergeSeq(){
        var flux1 = Flux.just("A", "B", "C").delayElements(Duration.ofMillis(100));
        var flux2 = Flux.just("D", "E", "F").delayElements(Duration.ofMillis(150));

        return Flux.mergeSequential(flux1, flux2).log();
    }


    public Flux<String> exploreMergeWith(){
        var mono1 = Mono.just("A");
        var mono2 = Mono.just("B");

        return mono1.mergeWith(mono2).log();
    }

    public Flux<String> exploreZip(){
        var flux1 = Flux.just("A", "B", "C");
        var flux2 = Flux.just("D", "E", "F");

        return Flux.zip(flux1, flux2, (s1, s2) -> s1+s2);
    }


    public Flux<String> exploreZipWith4(){
        var flux1 = Flux.just("A", "B", "C");
        var flux2 = Flux.just("D", "E", "F");

        var flux3 = Flux.just("1", "2", "3");
        var flux4 = Flux.just("4", "5", "6");

        return Flux.zip(flux1, flux2, flux3, flux4).map(objects -> objects.getT1() + objects.getT2()+ objects.getT3() + objects.getT4());
    }


    public Flux<String> exploreZipWith(){
        var mono1 = Mono.just("A");
        var mono2 = Mono.just("B");

        return mono1.concatWith(mono2);
    }
    public static void main(String[] args) {
        FluxAndMonoGeneratorService service = new FluxAndMonoGeneratorService();

        service.namesFlux().subscribe(System.out::println);

        service.namesMono().subscribe(System.out::println);
    }
}
