package com.bgr.future;

import java.util.Arrays;

public class StudentService {
    public static Student studentDetail(final Integer studentId){
        return new Student("Ahmet", Arrays.asList(80.0, 82.0, 72.4, 90.0));
    }
}
