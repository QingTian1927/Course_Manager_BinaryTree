package org.example;

import org.example.model.binaryTree.TreeNode;
import org.example.model.types.*;
import org.example.ui.Menu;
import org.example.util.Validation;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CourseManager {
    public static final String COURSE_SAVE_FILE = "data/courses.txt";
    public static final String STUDENT_SAVE_FILE = "data/students.txt";
    public static final String REGISTER_SAVE_FILE = "data/registers.txt";

    public static void main(String[] args) {

        RegisterList registerList = new RegisterList();
        CourseTree courseTree = new CourseTree(registerList);
        StudentTree studentTree = new StudentTree(registerList, courseTree);
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
                    manageCourseTree(courseTree);
                    break;
                case "2":
                    manageStudentTree(studentTree);
                    break;
                case "3":
                    manageRegisterList(registerList);
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

    public static void manageCourseTree(CourseTree courseTree) {
        while (true) {
            Menu.printCourseMenu();
            String choice = Validation.getString();
            System.out.println();

            String temp;
            switch (choice) {
                case "1":
                    courseTree.insert(courseTree.getCourseDetailsFromUser());
                    pressEnter();
                    break;
                case "2":
                    System.out.println("Course Tree pre-order traversal:\n");
                    courseTree.displayCourses(CourseTree.DISPLAY_PREORDER);
                    pressEnter();
                    break;
                case "3":
                    courseTree.save(new File(COURSE_SAVE_FILE));
                    pressEnter();
                    break;
                case "4":
                    System.out.print("Search by ccode: ");
                    TreeNode<Course> course = courseTree.get(Validation.getString());
                    CourseTree courseToDisplay = courseTree.convertTreeNodeToDisplay(course);
                    System.out.println("Search result:\n");
                    courseToDisplay.displayCourses(CourseTree.DISPLAY_BREADTH);
                    pressEnter();
                    break;
                case "5":
                    System.out.print("Delete code by copying: ");
                    TreeNode<Course> copyingDeleteNode = courseTree.get(Validation.getString().toUpperCase());
                    if (copyingDeleteNode == null) {
                        System.out.println("\nCourse not found.");
                    } else {
                        courseTree.deleteByCopying(copyingDeleteNode.data);
                        System.out.println("Course Tree after node deletion:\n");
                        courseTree.displayCourses(CourseTree.DISPLAY_BREADTH);
                        pressEnter();
                    }
                    break;
                case "6":
                    System.out.print("Delete code by merging: ");
                    TreeNode<Course> mergingDeleteNode = courseTree.get(Validation.getString().toUpperCase());
                    if (mergingDeleteNode == null) {
                        System.out.println("\nCourse not found.");
                    } else {
                        courseTree.deleteByMerging(mergingDeleteNode.data);
                        System.out.println("Course Tree after node deletion:\n");
                        courseTree.displayCourses(CourseTree.DISPLAY_BREADTH);
                        pressEnter();
                    }
                    break;
                case "7":
                    System.out.println("Tree before re-balancing:\n");
                    courseTree.displayCourses(CourseTree.DISPLAY_BREADTH);
                    courseTree.balance();
                    System.out.println("Tree after re-balancing:\n");
                    courseTree.displayCourses(CourseTree.DISPLAY_BREADTH);
                    pressEnter();
                    break;
                case "8":
                    System.out.println("Course Tree breadth-first traversal:\n");
                    courseTree.displayCourses(CourseTree.DISPLAY_BREADTH);
                    pressEnter();
                    break;
                case "9":
                    System.out.println("Number of courses: " + courseTree.getCourseCount());
                    pressEnter();
                    break;
                case "10":
                    System.out.print("Search by name: ");
                    CourseTree foundCourse = courseTree.searchByName(Validation.getString());
                    System.out.println();
                    System.out.println("Found courses: ");
                    foundCourse.displayCourses(CourseTree.DISPLAY_BREADTH);
                    pressEnter();
                    break;
                case "11":
                    System.out.print("Search by code: ");
                    courseTree.searchByCode(Validation.getString());
                    pressEnter();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
            System.out.println();
        }
    }

    public static void manageStudentTree(StudentTree studentTree) {
        while (true) {
            Menu.printStudentMenu();
            String choice = Validation.getString();
            System.out.println();

            switch (choice) {
                case "1":
                    studentTree.insert(studentTree.getStudentDetailsFromUser());
                    pressEnter();
                    break;
                case "2":
                    studentTree.inOrder(studentTree.getRoot());
                    break;
                case "3":
                    studentTree.save(new File(STUDENT_SAVE_FILE));
                    break;
                case "4":
                    System.out.print("Search by code: ");
                    TreeNode<Student> result = studentTree.searchByCode(Validation.getString());
                    System.out.println("Search result:\n");
                    System.out.println(result);
                    break;
                case "5":
                    System.out.print("Delete by code: ");
                    TreeNode<Student> deleteNode = studentTree.get(Validation.getString());
                    if (deleteNode == null) {
                        System.out.println("\nStudent not found.");
                    } else {
                        studentTree.deleteByCopying(deleteNode.data);
                        System.out.println("Student tree after deletion:\n");
                        studentTree.breadth();
                    }
                    break;
                case "6":
                    System.out.print("Search by name: ");
                    StudentTree foundStudents = studentTree.searchByName(Validation.getString());
                    System.out.println("Search result:\n");
                    foundStudents.breadth();
                    break;
                case "7":
                    System.out.print("Search registered courses by scode: ");
                    CourseTree foundCourses = studentTree.findRegisterCourse(Validation.getString());
                    System.out.println("Search result: ");
                    foundCourses.displayCourses(CourseTree.DISPLAY_BREADTH);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
            System.out.println();
        }
    }

    public static void manageRegisterList(RegisterList registerList) {
        while (true) {
            Menu.printRegisterMenu();
            String choice = Validation.getString();
            System.out.println();

            switch (choice) {
                case "1":
                    registerList.addLast(registerList.getRegisterDetailsFromUser());
                    System.out.println("Successfully added register");
                    break;
                case "2":
                    registerList.display();
                    break;
                case "3":
                    try {
                        registerList.save(new File(REGISTER_SAVE_FILE));
                        System.out.println("Successfully saved register list.");
                    } catch (IOException e) {
                        System.out.println("[FATAL] Cannot save to registers.txt");
                        e.printStackTrace();
                        System.exit(1);
                    }
                    break;
                case "4":
                    registerList.sort().display();
                    break;
                case "5":
                    registerList.updateMark();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
            System.out.println();
        }
    }

    public static void pressEnter() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nPress <ENTER> to continue: ");
        sc.nextLine();
    }
}
