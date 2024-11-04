package org.example.model.types;

import org.example.io.DataParser;
import org.example.model.binaryTree.TreeNode;
import org.example.model.linkedList.CommonList;
import org.example.model.linkedList.ListNode;
import org.example.util.Formatter;
import org.example.util.Validation;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class RegisterList extends CommonList<Register> {
    public static final String REGISTER_DATE_FORMAT = "yyyy-MM-dd";
    private CourseTree courseTree;
    private StudentTree studentTree;

    public RegisterList(StudentTree studentTree, CourseTree courseTree) {
        this.studentTree = studentTree;
        this.courseTree = courseTree;
    }

    public CourseTree getCourseTree() {
        return courseTree;
    }

    public void setCourseTree(CourseTree courseTree) {
        this.courseTree = courseTree;
    }

    public StudentTree getStudentTree() {
        return studentTree;
    }

    public void setStudentTree(StudentTree studentTree) {
        this.studentTree = studentTree;
    }

    public RegisterList() {
        super();
    }

    public void addLast(Register register) {
        super.addLast(register);
    }

    public void addFirst(Register register) {
        super.addLast(register);
    }

    public void deleteRegistrationwithCourse(String ccode) {
        for (ListNode<Register> p = head; p != null; p = p.next) {
            if (p.data.getScode().equals(ccode)) {
                this.delete(p);
            }
        }
    }

    public StudentTree findRegisterStudentByCourse(String ccode){
        StudentTree registerStudent = new StudentTree();
        for(ListNode<Register> p = head; p != null; p = p.next){
            if(p.data.getCcode().equals(ccode)){
                registerStudent.insert(studentTree.searchByCode(p.data.getScode()).data);
            }
        }
        return registerStudent;
    }

    public CourseTree findRegisterCourseByStudent(String scode) {
        CourseTree registerCourse = new CourseTree(this);

        for (ListNode<Register> p = head; p != null; p = p.next) {
            if (p.data.getScode().equals(scode)) {
                registerCourse.insert(
                  courseTree.searchByCode(p.data.getCcode()).data
                );
            }
        }
        return registerCourse;
    }

    public TreeNode<Student> findStudent(String scode, TreeNode<Student> root) {
        if (root == null) {
            return null;
        }
        if (root.data.getScode().equals(scode)) {
            return root;
        }
        if (scode.compareTo(root.data.getScode()) < 0) {
            return findStudent(scode, root.left);
        }
        return findStudent(scode, root.right);
    }

    public TreeNode<Course> findCourse(String ccode, TreeNode<Course> root) {
        if (root == null) {
            return null;
        }
        if (root.data.getCcode().equals(ccode)) {
            return root;
        }
        if (ccode.compareTo(root.data.getCcode()) < 0) {
            return findCourse(ccode, root.left);
        }
        return findCourse(ccode, root.right);
    }

    public ListNode<Register> findRegistration(String ccode, String scode) {
        ListNode<Register> current = super.head;

        while (current != null) {
            if (current.data.getCcode().equals(ccode) && current.data.getScode().equals(scode)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public Register getRegisterDetailsFromUser() {
        System.out.println("Please enter the following registration details:");

        System.out.print("Enter course code: ");
        String ccode = Validation.getString().toUpperCase();

        System.out.print("Enter student code: ");
        String scode = Validation.getString();

        LocalDate bdate = null;
        while (bdate == null) {
            System.out.print("Enter registration date (YYYY-MM-DD): ");
            String dateInput = Formatter.normalizeDate(Validation.getString());
            try {
                bdate = LocalDate.parse(dateInput, DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
            }
        }

        double mark = -1;
        while (mark < 0 || mark > 10) {
            mark = Validation.getDouble(
                    "Enter mark (0-10): ",
                    "Mark must be between 0 and 10.",
                    0, 10
            );
            if (mark < 0 || mark > 10) {
                System.out.println();
            }
        }

        int state = (mark >= 5) ? 1 : 0;

        return new Register(ccode, scode, bdate, mark, state);
    }

    public void registerCourse(
            String ccode,
            String scode,
            TreeNode<Course> courseRoot,
            TreeNode<Student> studentRoot
    ) {
        ListNode<Register> existingRegister = findRegistration(ccode, scode);

        if (existingRegister != null) {
            System.out.println("Student " + scode + " has already registered for the course " + ccode);
            return;
        }

        TreeNode<Course> courseNode = findCourse(ccode.trim(), courseRoot);
        TreeNode<Student> studentNode = findStudent(scode.trim(), studentRoot);

        if (courseNode == null) {
            System.out.println("Course does not exist.");
            return;
        }

        if (studentNode == null) {
            System.out.println("Student does not exist.");
            return;
        }

        Course course = courseNode.data;
        Student student = studentNode.data;

        if (course.getSeats() <= 0) {
            System.out.println("No seats available for the course.");
            return;
        }

        LocalDate today = LocalDate.now();
        Register newRegistration = new Register(ccode, scode, today, 0, 0);
        addFirst(newRegistration);

        course.updateSeatAndRegister(-1, 1);
        System.out.println("Course successfully registered for student: " + scode);
    }

    public void updateMark() {
        System.out.print("Enter ccode: ");
        String ccode = Validation.getString();
        System.out.print("Enter scode: ");
        String scode = Validation.getString();

        ListNode<Register> current = head;
        while (current != null) {
            if (scode.equals(current.data.getScode()) && ccode.equals(current.data.getCcode())) {
                double newMark = Validation.getDouble(
                        "Enter new mark: ",
                        "Mark must be between 0 and 10",
                        0, 10
                );
                current.data.setMark(newMark);
                System.out.println("Mark Updated successfully.");
                return;
            }
            current = current.next;
        }
        System.out.println("Registration not found. Update mark failed!");
    }

    public void deleteRegister(String scode) {
        ListNode<Register> current = head;

        while (current != null) {
            if (current.data.getScode().equals(scode)) {
                this.delete(current);
            }
            current = current.next;
        }
    }

    private boolean shouldSwap(ListNode<Register> a, ListNode<Register> b) {
        int ccodeComparison = a.data.getCcode().compareTo(b.data.getCcode());
        if (ccodeComparison > 0) {
            return true;
        }
        if (ccodeComparison == 0) {
            return a.data.getScode().compareTo(b.data.getScode()) > 0;
        }
        return false;
    }

    @Override
    public CommonList<Register> sort() {
        if (head == null) {
            return null;
        }

        for (ListNode<Register> i = head; i != null; i = i.next) {
            ListNode<Register> minNode = i;

            for (ListNode<Register> j = i.next; j != null; j = j.next) {
                if (shouldSwap(minNode, j)) {
                    minNode = j;
                }
            }

            if (minNode != i) {
                swap(i, minNode);
            }
        }

        return this;
    }

    @Override
    public void display() {
        if (head == null) {
            System.out.println("No registrations available.");
            return;
        }

        ListNode<Register> current = head;
        System.out.println("--------------------------------------------------------------");
        System.out.printf(
                "%-10s | %-10s | %-20s | %-5s | %s\n",
                "CourseID", "StudentID", "Registration Date", "Mark", "State"
        );
        System.out.println("--------------------------------------------------------------");

        while (current != null) {
            System.out.printf(
                    "%-10s | %-10s | %-20s | %-5.3f | %d\n",
                    current.data.getCcode(),
                    current.data.getScode(),
                    current.data.getBdate(),
                    current.data.getMark(),
                    current.data.getState()
            );
            current = current.next;
        }
    }

    public void load(File file) throws IOException {
        DataParser<Register> dataParser = (String data) -> {
            String[] properties = data.split(DataParser.PROPERTY_SEPARATOR);
            if (properties.length != 5) {
                return null;
            }

            String ccode = properties[0].trim();
            String scode = properties[1].trim();

            LocalDate bdate;
            try {
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(REGISTER_DATE_FORMAT);
                bdate = LocalDate.parse(properties[2].trim(), dateFormat);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format: " + properties[2]);
                return null;
            }

            String markString = properties[3].trim().replace(",", ".");
            double mark = Double.parseDouble(markString);
            if (mark < 0 || mark > 10) {
                System.out.println("Invalid mark: " + properties[3]);
                return null;
            }

            int state = Integer.parseInt(properties[4].trim());
            if (!Validation.isBooleanInt(state)) {
                System.out.println("Invalid state: " + properties[4]);
                return null;
            }

            return new Register(ccode, scode, bdate, mark, state);
        };

        this.readFile(file, dataParser);
    }

    public void save(File file) throws IOException {
        this.saveFile(file, Register::toDataString);
    }
}
