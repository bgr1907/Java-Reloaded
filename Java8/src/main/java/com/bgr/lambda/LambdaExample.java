package com.bgr.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LambdaExample {

    public static void main(String[] args){

        final Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Inner Class");
            }
        };

        Runnable runnable2 = () -> System.out.println("Lambda");

        runnable1.run();
        runnable2.run();

        List<Student> students = Arrays.asList(new Student("Halil", 8.8),
                new Student("Ahmet", 7.4),
                new Student("Kemal", 9.0));

        Comparator<Student> studentComparator = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getGpa().compareTo(o2.getGpa());
            }
        };

        Comparator<Student> byGpa = (o1,o2) -> o1.getGpa().compareTo(o2.getGpa());
        Comparator<Student> byGpa2 = Comparator.comparing(Student::getGpa);
    }
}

class Student {
    private String name;
    private Double gpa;

    public Student(String name, Double gpa) {
        this.name = name;
        this.gpa = gpa;
    }

    public Double getGpa() {
        return gpa;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }
}
