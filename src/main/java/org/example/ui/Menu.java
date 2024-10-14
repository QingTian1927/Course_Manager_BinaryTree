package org.example.ui;

public final class Menu {
    private Menu() {
    }

    public static void printMenu() {
        System.out.println("CSD201 BinaryTree CourseManager");
        System.out.println("===============================\n");
        System.out.println("1. Manage Course Tree");
        System.out.println("2. Manage Student Tree");
        System.out.println("3. Manage Register List");
        System.out.println("0. Exit\n");
        System.out.print("Enter your choice: ");
    }

    public static void printCourseMenu() {
        System.out.println("----- Manage Course Tree -----\n");
        System.out.println("1. Input & add to tree");
        System.out.println("2. Display data by pre-order traversal");
        System.out.println("3. Save course tree by post-order traversal");
        System.out.println("4. Search by ccode");
        System.out.println("5. Delete bcode by copying");
        System.out.println("6. Delete bcode by merging");
        System.out.println("7. Balance tree");
        System.out.println("8. Display data by breadth-first traversal");
        System.out.println("9. Count courses");
        System.out.println("10. Search course by name");
        System.out.println("11. Search course by code");
        System.out.println("0. Exit\n");
        System.out.print("Enter your choice: ");
    }

    public static void printStudentMenu() {
        System.out.println("----- Manage Student Tree -----\n");
        System.out.println("1. Input & add to tree");
        System.out.println("2. Display data by in-order traversal");
        System.out.println("3. Save student tree by post-order traversal");
        System.out.println("4. Search by scode");
        System.out.println("5. Delete scode by copying");
        System.out.println("6. Search by name");
        System.out.println("7. Search registered course by scode");
        System.out.println("0. Exit\n");
        System.out.print("Enter your choice: ");
    }

    public static void printRegisterMenu() {
        System.out.println("----- Manage Register List -----\n");
        System.out.println("1. Input & add to the end");
        System.out.println("2. Display data");
        System.out.println("3. Save register list to file");
        System.out.println("4. Sort by ccode and scode");
        System.out.println("5. Update mark by ccode and scode");
        System.out.println("0. Exit\n");
        System.out.print("Enter your choice: ");
    }
}
