package com.example.advancedassignment1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

@SpringBootApplication
public class AdvancedAssignment1Application {

    public static void main(String[] args) {
        ArrayList<Integer> grades = new ArrayList<>();
        ArrayList<Integer> quizGrades = new ArrayList<>();
        Scanner key = new Scanner(System.in);
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        // Used so, say instead of having 3 pieces of homework, any number could be used.
        int piecesOfWork = 0;
        int quizes = 0;
        // Temporary variable so each grade can be checked if it's a valid number
        int currentGrade = 0;
        // Used to get out of loops looking for errors
        boolean validResult = false;
        // Used to check if work was submitted on time
        String onTime;
        // Since this is heavily focused on preventing errors and that sort of thing so expect a lot of loops
        // Not in a separate class, as it is generally not good practice to put println statements in classes which are meant to be reusable
        // and can't really be in a method since it has to return piecesOfWork at some point, and that doesn't play well with an infinite loop
        // Same goes for all of the checks
        while (!validResult) {
            System.out.println("Enter number of pieces of homework: ");
            // Use try and catch to prevent errors
            try {
                piecesOfWork = key.nextInt();
                if (piecesOfWork < 0) {
                    System.out.println("Can't have negative pieces of homework! Try again!");
                } else {
                    validResult = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Try again!");
                // "Eats" the invalid input to avoid infinite loops
                key.next();
            }
        }
        validResult = false;
        // Loops through the numbers and adds them to the ArrayList only if they're valid.
        // Only punished if a grade was submitted after 24 hours
        for (int i = 0; i < piecesOfWork; i++) {
            while (!validResult) {
                System.out.println("Enter the grade of piece #" +(i+1)+ " (should be out of 100): ");
                try {
                    currentGrade = key.nextInt();
                    if (currentGrade < 0 || currentGrade > 100) {
                        System.out.println("Invalid input! Try again!");
                    } else {
                        System.out.println("Was homework #" + (i+1) + " submitted on time? (y or n)");
                        key.nextLine();
                        onTime = key.nextLine();
                        // Assuming yes if input is not y or n
                        if (onTime.equalsIgnoreCase("n")) {
                            System.out.println("Was it submitted within the 24 hours of the due date? (y or n)");
                            onTime = key.nextLine();
                            if (onTime.equalsIgnoreCase("n")) {
                                currentGrade = 0;
                            }
                        }
                        grades.add(currentGrade);
                        validResult = true;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Try again!");
                    key.next();
                }

            }
            validResult = false;
        }

        while (!validResult) {
            System.out.println("Enter the prelim grade (should be out of 100): ");
            try {
                currentGrade = key.nextInt();
                if (currentGrade < 0 || currentGrade > 100) {
                    System.out.println("Invalid input! Try again!");
                } else {
                    grades.add(currentGrade);
                    validResult = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Try again!");
                key.next();
            }
        }
        validResult = false;
        while (!validResult) {
            System.out.println("Enter the final grade (should be out of 100): ");
            try {
                currentGrade = key.nextInt();
                if (currentGrade < 0 || currentGrade > 100) {
                    System.out.println("Invalid input! Try again!");
                } else {
                    grades.add(currentGrade);
                    validResult = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Try again!");
                key.next();
            }

        }
        validResult = false;

        while (!validResult) {
            System.out.println("Enter number of quizes: ");
            // Use try and catch to prevent errors
            try {
                quizes = key.nextInt();
                if (quizes < 0) {
                    System.out.println("Can't have negative pieces of homework! Try again!");
                } else {
                    validResult = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Try again!");
                // "Eats" the invalid input to avoid infinite loops
                key.next();
            }
        }

        validResult = false;
        for (int i = 0; i < quizes; i++) {
            while (!validResult) {
                System.out.println("Enter the grade of quiz #" +(i+1)+ " (should be out of 10): ");
                try {
                    currentGrade = key.nextInt();
                    if (currentGrade < 0 || currentGrade > 10) {
                        System.out.println("Invalid input! Try again!");
                    } else {
                        quizGrades.add(currentGrade);
                        validResult = true;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Try again!");
                    key.next();
                }

            }
            validResult = false;
        }

        // Create the grade calculator and calculate
        GradeCalculator gradeCalculator = new GradeCalculator(grades, quizGrades);
        gradeCalculator.calculate();
        System.out.println("Your final course score is: " + decimalFormat.format(gradeCalculator.getAverage()) + "\n" +
                "Your letter grade will be at least: " + gradeCalculator.getLetterGrade());

    }

}
