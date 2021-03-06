package com.bgr.behavior;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BehaviorParameterization {

    public static void main(String[] args){
        final List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        //System.out.println(getOddNumbers(numbers));
        //System.out.println(getNumbersByBehavior(numbers, new OddNumbers()));
        //System.out.println(getNumbersByBehavior(numbers, new EvenNumbers()));

        /*System.out.println(getNumbersByBehavior(numbers, new NumberPredicate<Integer>() {
            @Override
            public boolean test(Integer number) {
                return number > 4;
            }
        }));*/

        List<String> names = Arrays.asList("Halil", "İbrahim", "İpek", "Yavuz", "Kemal Beyaz", "Fatih", "Mithat", "Evren", "Altuğ");

        //System.out.println(getNumbersByBehavior(numbers, (n) -> n % 2 == 0));
        //System.out.println(getNumbersByBehavior(numbers, (n) -> n % 2 == 1));
        //System.out.println(getNumbersByBehavior(numbers, (n) -> n > 4));
        //numbers.forEach(System.out::println);

        getValuesBehavior(numbers, n -> n < 9 ).forEach(System.out::println);
        getValuesBehavior(names, s -> s.contains("a")).forEach(System.out::println);
    }

    public static <T> Collection<T> getValuesBehavior(final Collection<T> list, final Predicate<T> predicate) {
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    public static List<Integer> getNumbersByBehavior(final List<Integer> list, final NumberPredicate<Integer> predicate){
        final List<Integer> result = new ArrayList<Integer>();
        for(Integer number: list){
            if(predicate.test(number)){
                result.add(number);
            }
        }
        return result;
    }

    public static List<Integer> getOddNumbers(final List<Integer> list){
        final List<Integer> result = new ArrayList<Integer>();
        for(Integer number: list){
            if(number % 2 == 1){
                result.add(number);
            }
        }
        return result;
    }
}
