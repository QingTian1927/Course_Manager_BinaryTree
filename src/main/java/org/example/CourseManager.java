package org.example;

import org.example.model.types.Course;
import org.example.model.types.CourseTree;
import org.example.model.types.RegisterList;
import org.example.model.types.StudentTree;
import org.example.ui.Menu;
import org.example.util.Validation;

import java.io.File;
import java.io.IOException;

public class CourseManager {
    public static final String COURSE_SAVE_FILE = "data/courses.txt";
    public static final String STUDENT_SAVE_FILE = "data/students.txt";
    public static final String REGISTER_SAVE_FILE = "data/registers.txt";

    public static void main(String[] args) {

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

            switch (choice) {
                case "1":
                    break;
                case "2":
                    courseTree.preOrder(courseTree.getRoot());
                    break;
                case "3":
                    courseTree.save(new File(COURSE_SAVE_FILE));
                    break;
                case "4":
                    System.out.print("Search by code: ");
                    courseTree.searchByCode(Validation.getString());
                    break;
                case "5":
                    System.out.print("Delete by code: ");
                    courseTree.deleteByCopying(courseTree.get(Validation.getString()).data);
                    break;
                case "6":
                    System.out.print("Delete by code: ");
                    courseTree.deleteByMerging(courseTree.get(Validation.getString()).data);
                    break;
                case "7":
                    courseTree.balance();
                    break;
                case "8":
                    courseTree.breadth();
                    break;
                case "9":
                    System.out.println("Number of courses: " + courseTree.getCourseCount());
                    break;
                case "10":
                    CourseTree foundCourse = courseTree.searchByName(Validation.getString());
                    foundCourse.breadth();
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

    public static void manageStudentTree(StudentTree studentTree) {
        while (true) {
            Menu.printStudentMenu();
            String choice = Validation.getString();
            System.out.println();

            switch (choice) {
                case "1":
                    break;
                case "2":
                    studentTree.inOrder(studentTree.getRoot());
                    break;
                case "3":
                    studentTree.save(new File(STUDENT_SAVE_FILE));
                    break;
                case "4":
                    System.out.print("Search by code: ");
                    studentTree.searchByCode(Validation.getString());
                    break;
                case "5":
                    System.out.print("Delete by code: ");
                    studentTree.deleteByCopying(studentTree.get(Validation.getString()).data);
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

    public static void manageRegisterList(RegisterList registerList) {
        while (true) {
            Menu.printRegisterMenu();
            String choice = Validation.getString();
            System.out.println();

            switch (choice) {
                case "1":
                    break;
                case "2":
                    registerList.display();
                    break;
                case "3":
                    try {
                        registerList.save(new File(REGISTER_SAVE_FILE));
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
