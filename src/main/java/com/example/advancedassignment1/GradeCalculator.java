package com.example.advancedassignment1;

import java.util.ArrayList;
import java.util.Collections;

// There are 2 ArrayLists, one for the grades out of 100 and one for the grades out of 10
// Since it was not said how/if the prelim/finals/quizzes are weighted differently, I made them weighted the same
public class GradeCalculator {
    private ArrayList<Integer> grades;
    private ArrayList<Integer> quizGrades;
    private double average, gradesAverage, quizAverage;

    public GradeCalculator(ArrayList<Integer> grades, ArrayList<Integer> quizGrades) {
        this.grades = new ArrayList<>();
        this.quizGrades = new ArrayList<>();
        this.grades = grades;
        this.quizGrades = quizGrades;
    }

    // Separate constructor for different use cases, quizGrades is still initialized to avoid errors
    public GradeCalculator(ArrayList<Integer> grades) {
        this.grades = new ArrayList<>();
        this.quizGrades = new ArrayList<>();
        this.grades = grades;
    }

    public void setGrades(ArrayList<Integer> grades) {
        this.grades = grades;
    }

    public ArrayList<Integer> getGrades() {
        return grades;
    }

    public void setQuizGrades(ArrayList<Integer> quizGrades) {
        this.quizGrades = quizGrades;
    }

    public ArrayList<Integer> getQuizGrades() {
        return quizGrades;
    }

    // Calculation of average and returning the average are separate classes for modularity
    public void calculateGradesAverage() {
        gradesAverage = 0;
        for (Integer grade : grades) {
            gradesAverage += grade;
        }
        gradesAverage = gradesAverage / grades.size();
    }

    public double getGradesAverage() {
        return gradesAverage;
    }

    // Multiplies all the quiz grades by 10 since they're out of 10 and not 100
    public void fixQuizGrades() {
        for (int i = 0; i < quizGrades.size(); i++) {
            quizGrades.set(i, quizGrades.get(i)*10);
        }
    }

    public void calculateQuizAverage() {
        quizAverage = 0;
        // Use Collections to get the lowest and subtract it from the sum before averaging
        int lowQuiz = Collections.min(quizGrades);

        for (Integer grade : quizGrades) {
            quizAverage += grade;
        }

        quizAverage -= lowQuiz;
        // To prevent divding by zero
        if (quizGrades.size()-1 <= 0) {
            quizAverage = quizAverage/1;
        } else {
            quizAverage = quizAverage / quizGrades.size()-1;
        }

    }

    public double getQuizAverage() {
        return quizAverage;
    }

    public void calculateAverage() {
        average = 0;
        average = (gradesAverage + quizAverage) / 2;
    }

    public double getAverage() {
        return average;
    }

    public String getLetterGrade() {
        if (average >= 94) {
            return "A";
        } else if (average < 94 && average >= 90) {
            return "A-";
        } else if (average < 90 && average >= 87) {
            return "B+";
        } else if (average < 87 && average >= 84) {
            return "B";
        } else if (average < 84 && average >= 80) {
            return "B-";
        } else if (average < 80 && average >= 77) {
            return "C+";
        } else if (average < 77 && average >= 74) {
            return "C";
        } else if (average < 74 && average >= 70) {
            return "C-";
        } else if (average < 70 && average >= 67) {
            return "D+";
        } else if (average < 67 && average >= 64) {
            return "D";
        } else if (average < 64 && average >= 60) {
            return "D-";
        } else {
            return "F";
        }
    }

    // Having all of the methods separately for falling in with OOP principles is great, but having a method that calculates everything makes things easier as well
    public void calculate() {
        fixQuizGrades();
        calculateQuizAverage();
        calculateGradesAverage();
        calculateAverage();
    }
}

