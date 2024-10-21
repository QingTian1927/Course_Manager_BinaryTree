package org.example;

import org.example.model.types.CourseTree;
import org.example.model.types.RegisterList;
import org.example.model.types.StudentTree;
import org.example.ui.Menu;
import org.example.util.Validation;

import java.io.File;
import java.io.IOException;

public class CourseManager {
    public static void main(String[] args) {
        final String COURSE_SAVE_FILE = "data/courses.txt";
        final String STUDENT_SAVE_FILE = "data/students.txt";
        final String REGISTER_SAVE_FILE = "data/registers.txt";

        RegisterList registerList = new RegisterList();
        StudentTree studentTree = new StudentTree(registerList);
        CourseTree courseTree = new CourseTree(registerList);
        registerList.setCourseTree(courseTree);
        registerList.setStudentTree(studentTree);

        studentTree.load(new File(STUDENT_SAVE_FILE));
        courseTree.load(new File(COURSE_SAVE_FILE));

        try {
            registerList.load(new File(REGISTER_SAVE_FILE));
        } catch (IOException e) {
            System.out.println("[FATAL] Failed to read registers.txt");
            e.printStackTrace();
            System.exit(1);
        }

        boolean isRunning = true;
        while (isRunning) {
            Menu.printMenu();
            String choice = Validation.getString();
            System.out.println();

            switch (choice) {
                case "1":
                    manageCourseTree();
                    break;
                case "2":
                    manageStudentTree();
                    break;
                case "3":
                    manageRegisterList();
                    break;
                case "0":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
        System.out.println();
    }

    public static void manageCourseTree() {
        while (true) {
            Menu.printCourseMenu();
            String choice = Validation.getString();
            System.out.println();

            switch (choice) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    break;
                case "7":
                    break;
                case "8":
                    break;
                case "9":
                    break;
                case "10":
                    break;
                case "11":
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
            System.out.println();
        }
    }

    public static void manageStudentTree() {
        while (true) {
            Menu.printStudentMenu();
            String choice = Validation.getString();
            System.out.println();

            switch (choice) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    break;
                case "7":
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
            System.out.println();
        }
    }

    public static void manageRegisterList() {
        while (true) {
            Menu.printRegisterMenu();
            String choice = Validation.getString();
            System.out.println();

            switch (choice) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
            System.out.println();
        }
    }
}
