package com.bgr.future;

import java.util.List;

public class Student {

    private String name;
    private List<Double> grade;

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, List<Double> grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Double> getGrade() {
        return grade;
    }

    public void setGrade(List<Double> grade) {
        this.grade = grade;
    }
}
