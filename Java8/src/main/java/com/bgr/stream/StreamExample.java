package com.bgr.stream;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamExample {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        List<Car> carList = Arrays.asList(new Car("White", 2000),
                new Car("Black", 2010),
                new Car("Red", 2020));


        List<String> colors = carList
                .stream()
                    // Stream<Car>
                    .map(Car::getColor) // Stream<String>
                    .collect(Collectors.toList());

        List<String> title = Arrays.asList("title1", "title1");
        Stream<String> titleStream = title.stream();
        titleStream.forEach(System.out::println);
        //titleStream.forEach(System.out::println); // java.lang.IllegalStateException: stream has already been operated upon or closed

        colors.forEach(System.out::println);

        numbers
                .stream()
                    .map(n -> n * 2)
                    .filter(n -> n < 8)
                    .skip(2)
                    .limit(3)
                    .forEach(System.out::println);

        Stream.of("A", "B", "C");

        System.out.println(readFile());

        Stream.generate(Math::random)
                .limit(5).forEach(System.out::println);

        Stream.iterate(1, i -> i+1)
                .filter(i -> i % 2 == 0)
                .limit(20)
                .forEach(System.out::println);

        String[] words = {"Hello", "World"};
        Stream<String> streamOfWords = Arrays.stream(words);

        /*streamOfWords
                .map(word -> word.split(""))
                .map(Arrays::stream) // Stream<String[]>
                .distinct()
                .collect(Collectors.toList())
                .forEach(result -> result.forEach(System.out::println));*/

        streamOfWords
                .map(word -> word.split(""))
                .flatMap(Arrays::stream) // Stream<String[]>
                .distinct()
                .collect(Collectors.toList())
                .forEach(System.out::println);

        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5);

        int count = numberList.stream().reduce(0, (a, b) -> a + b);
        System.out.println("Result: " + count);


        List<Car> carList1 = Arrays.asList(new Car("Black", 2010),
                new Car("White", 2010),
                new Car("Black", 2010),
                new Car("Red", 2011),
                new Car("Black", 2011),
                new Car("White", 2012),
                new Car("Orange", 2012),
                new Car("Black", 2012));

        Map<Integer, List<Car>> carMap =
                carList1.stream().collect(Collectors.groupingBy(Car::getModel));

        carMap.forEach((k, v) -> {
            System.out.println("Key"+ k);
            System.out.println("Value"+ v);
        });

        System.out.println("Sequential sum " +
        meausure(StreamExample::sequentialSum,10_000_000) + " ms");



    }

    public static long meausure(Function<Long, Long> adder, long n){

        long fastest = Long.MAX_VALUE;
        long start = System.nanoTime();
        for(int i = 0; i < 10; i++){
            long sum = adder.apply(n);
            System.out.println("Result: " + sum);
        }

        long duration = (System.nanoTime() - start) / 1_000_000;
        if(duration < fastest) {
            fastest = duration;
        }

        return fastest;
    }

    public static long sequentialSum(final long n) {
        return LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    public static long iterativeSum(final long n) {
        long result = 0;
        for(long i = 1L; i <= n; i++){
            result +=i;
        }
        return result;
    }

    public static long readFile() {
        long uniqueWords = 0;

        try(Stream<String> lines = Files
                .lines(Paths.get("Java8/src/main/resources/data.txt"), Charset.defaultCharset())){

            uniqueWords = lines
                    .flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct().count();

        } catch (IOException ex) {
            System.out.println(ex);
        }

        return uniqueWords;
    }
}

class Car{

    private String color;
    private int model;

    public Car(String color, int model) {
        this.color = color;
        this.model = model;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "color='" + color + '\'' +
                ", model=" + model +
                '}';
    }
}