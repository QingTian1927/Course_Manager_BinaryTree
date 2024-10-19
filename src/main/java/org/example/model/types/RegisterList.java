package org.example.model.types;

import org.example.model.binaryTree.TreeNode;
import org.example.model.linkedList.CommonList;
import org.example.model.linkedList.ListNode;

import java.time.LocalDate;

public class RegisterList extends CommonList<Register> {
    private CourseTree courseTree;
    private StudentTree studentTree;

    public RegisterList(StudentTree studentTree, CourseTree courseTree) {
        this.studentTree = studentTree;
        this.courseTree = courseTree;
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

    public CourseTree findRegisterCourseByStudent(String scode){
        CourseTree registerCourse = new CourseTree();
        for(ListNode<Register> p = head; p != null; p = p.next){
            if(p.data.getScode().equals(scode)){
                registerCourse.insert(courseTree.searchByCode(p.data.getCcode()).data);
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
        } else if (scode.compareTo(root.data.getScode()) < 0) {
            return findStudent(scode, root.left);
        } else {
            return findStudent(scode, root.right);
        }
    }

    public TreeNode<Course> findCourse(String ccode, TreeNode<Course> root) {
        if (root == null) {
            return null;
        }

        if (root.data.getCcode().equals(ccode)) {
            return root;
        } else if (ccode.compareTo(root.data.getCcode()) < 0) {
            return findCourse(ccode, root.left);
        } else {
            return findCourse(ccode, root.right);
        }
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

    public void updateMark(String scode, String ccode, double newmark) {
        ListNode<Register> current = head;
        while (current != null) {
            if (scode.equals(current.data.getScode()) && ccode.equals(current.data.getCcode())) {
                current.data.setMark(newmark);
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
        return;
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

            //System.out.println("---------------------");
            current = current.next;
        }
    }
}
