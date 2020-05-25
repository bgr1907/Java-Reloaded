package com.bgr.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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
        //titleStream.forEach(System.out::println);

        colors.forEach(System.out::println);

        numbers
                .stream()
                    .map(n -> n * 2)
                    .filter(n -> n < 8)
                    .skip(2)
                    .limit(3)
                    .forEach(System.out::println);
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
}