package com.bgr.behavior;

public class EvenNumbers implements NumberPredicate<Integer> {
    @Override
    public boolean test(Integer number) {
        return number % 2 == 0;
    }
}
