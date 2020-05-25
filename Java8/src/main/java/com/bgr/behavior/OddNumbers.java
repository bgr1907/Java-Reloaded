package com.bgr.behavior;

public class OddNumbers implements NumberPredicate<Integer> {
    @Override
    public boolean test(Integer number) {
        return number % 2 == 1;
    }
}
