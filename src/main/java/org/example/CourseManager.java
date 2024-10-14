package org.example;

import org.example.ui.Menu;
import org.example.util.Validation;

public class CourseManager {
    public static void main(String[] args) {
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
