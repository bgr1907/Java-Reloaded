package com.bgr.future;

import java.util.OptionalDouble;

public class GradeService {

    public static OptionalDouble getGradeAverage(final Student student){
        return student.getGrade().stream().mapToDouble(x -> x).average();
    }
}
