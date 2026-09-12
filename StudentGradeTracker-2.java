package codeAlpha_StudentGradeTracker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Student {

    int rollNo;
    String name;
    int marks;

    Student(int rollNo, String name, int marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.marks = marks;
    }

    String getGrade() {

        if (marks >= 90)
            return "A+";
        else if (marks >= 80)
            return "A";
        else if (marks >= 70)
            return "B";
        else if (marks >= 60)
            return "C";
        else if (marks >= 50)
            return "D";
        else
            return "F";
    }

    String getStatus() {

        if (marks >= 40)
            return "PASS";
        else
            return "FAIL";
    }
}

public class StudentGradeTracker {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {

        int choice;

        do {

            showHeader();
            showMenu();

            System.out.print("Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.println("Invalid input! Enter a number.");
                sc.next();
            }

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    displayStudents();
                    break;

                case 3:
                    showStatistics();
                    break;

                case 4:
                    searchStudent();
                    break;

                case 5:
                    showTopPerformers();
                    break;

                case 6:
                    System.out.println("\nThank you for using Student Grade Tracker!");
                    break;

                default:
                    System.out.println("\nInvalid choice! Please try again.");
            }

        } while (choice != 6);

        sc.close();
    }

    // ================= HEADER =================

    static void showHeader() {

        System.out.println("\n");
        System.out.println("==============================================");
        System.out.println("          STUDENT GRADE TRACKER");
        System.out.println("             CodeAlpha Internship");
        System.out.println("==============================================");
    }

    // ================= MENU =================

    static void showMenu() {

        System.out.println("\n--------------- MAIN MENU ----------------");
        System.out.println("1. Add Student");
        System.out.println("2. Display All Students");
        System.out.println("3. View Class Statistics");
        System.out.println("4. Search Student");
        System.out.println("5. View Top 3 Performers");
        System.out.println("6. Exit");
        System.out.println("-------------------------------------------");
    }

    // ================= ADD STUDENT =================

    static void addStudent() {

        System.out.println("\n========== ADD STUDENT ==========");

        System.out.print("Enter Roll Number: ");
        int rollNo = sc.nextInt();
        sc.nextLine();

        // Check duplicate roll number
        for (Student s : students) {

            if (s.rollNo == rollNo) {
                System.out.println("Roll number already exists!");
                return;
            }
        }

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        int marks;

        while (true) {

            System.out.print("Enter Marks (0-100): ");

            if (sc.hasNextInt()) {

                marks = sc.nextInt();
                sc.nextLine();

                if (marks >= 0 && marks <= 100) {
                    break;
                }

                System.out.println("Marks must be between 0 and 100.");

            } else {

                System.out.println("Please enter valid marks.");
                sc.next();
            }
        }

        Student student = new Student(rollNo, name, marks);

        students.add(student);

        System.out.println("\nStudent added successfully!");
        System.out.println("Name   : " + name);
        System.out.println("Marks  : " + marks);
        System.out.println("Grade  : " + student.getGrade());
        System.out.println("Status : " + student.getStatus());
    }

    // ================= DISPLAY STUDENTS =================

    static void displayStudents() {

        if (students.isEmpty()) {

            System.out.println("\nNo student records available.");
            return;
        }

        System.out.println("\n================ STUDENT REPORT ================");

        System.out.printf("%-8s %-18s %-8s %-10s %-8s %-8s%n",
                "Roll", "Name", "Marks", "Percent", "Grade", "Status");

        System.out.println(
                "--------------------------------------------------------------");

        for (Student s : students) {

            System.out.printf("%-8d %-18s %-8d %-10.2f %-8s %-8s%n",
                    s.rollNo,
                    s.name,
                    s.marks,
                    (double) s.marks,
                    s.getGrade(),
                    s.getStatus());
        }

        System.out.println(
                "--------------------------------------------------------------");
    }

    // ================= STATISTICS =================

    static void showStatistics() {

        if (students.isEmpty()) {

            System.out.println("\nPlease add students first.");
            return;
        }

        int total = 0;
        int highest = students.get(0).marks;
        int lowest = students.get(0).marks;

        String highestStudent = students.get(0).name;
        String lowestStudent = students.get(0).name;

        int pass = 0;
        int fail = 0;

        for (Student s : students) {

            total += s.marks;

            if (s.marks > highest) {

                highest = s.marks;
                highestStudent = s.name;
            }

            if (s.marks < lowest) {

                lowest = s.marks;
                lowestStudent = s.name;
            }

            if (s.marks >= 40)
                pass++;
            else
                fail++;
        }

        double average = (double) total / students.size();

        double passPercentage =
                ((double) pass / students.size()) * 100;

        System.out.println("\n==============================================");
        System.out.println("             CLASS STATISTICS");
        System.out.println("==============================================");

        System.out.println("Total Students  : " + students.size());

        System.out.printf("Average Marks   : %.2f%n", average);

        System.out.println("Highest Marks   : " + highest);
        System.out.println("Highest Scorer  : " + highestStudent);

        System.out.println("Lowest Marks    : " + lowest);
        System.out.println("Lowest Scorer   : " + lowestStudent);

        System.out.println("Passed Students : " + pass);
        System.out.println("Failed Students : " + fail);

        System.out.printf("Pass Percentage : %.2f%%%n", passPercentage);

        System.out.println("==============================================");
    }

    // ================= SEARCH STUDENT =================

    static void searchStudent() {

        if (students.isEmpty()) {

            System.out.println("\nNo students available.");
            return;
        }

        System.out.print("\nEnter Roll Number to Search: ");

        int rollNo = sc.nextInt();
        sc.nextLine();

        for (Student s : students) {

            if (s.rollNo == rollNo) {

                System.out.println("\n========== STUDENT FOUND ==========");

                System.out.println("Roll Number : " + s.rollNo);
                System.out.println("Name        : " + s.name);
                System.out.println("Marks       : " + s.marks);
                System.out.println("Percentage  : " + s.marks + "%");
                System.out.println("Grade       : " + s.getGrade());
                System.out.println("Status      : " + s.getStatus());

                return;
            }
        }

        System.out.println("\nStudent not found!");
    }

    // ================= TOP PERFORMERS =================

    static void showTopPerformers() {

        if (students.isEmpty()) {

            System.out.println("\nNo student records available.");
            return;
        }

        ArrayList<Student> sortedStudents =
                new ArrayList<>(students);

        Collections.sort(sortedStudents,
                Comparator.comparingInt((Student s) -> s.marks)
                        .reversed());

        System.out.println("\n==============================================");
        System.out.println("              TOP 3 PERFORMERS");
        System.out.println("==============================================");

        int rank = 1;

        for (Student s : sortedStudents) {

            System.out.println(
                    "Rank " + rank +
                    " | " + s.name +
                    " | Marks: " + s.marks +
                    " | Grade: " + s.getGrade());

            rank++;

            if (rank > 3)
                break;
        }

        System.out.println("==============================================");
    }
}
