package org.example.model.types;

import org.example.io.DataParser;
import org.example.io.DataWriter;
import org.example.model.binaryTree.CommonTreeInterface;
import org.example.model.binaryTree.TreeNode;
import org.example.model.linkedList.CommonQueue;
import org.example.util.Validation;
import sun.reflect.generics.tree.Tree;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class CourseTree implements CommonTreeInterface<Course> {
    private TreeNode<Course> root;
    private  RegisterList registerList;

    public CourseTree(){}

    public CourseTree(RegisterList registerList){
        this.registerList = registerList;
    }

    public TreeNode<Course> getRoot() {
        return root;
    }

    public int getCourseCount() {
        return this.countCourses(this.root);
    }

    private int countCourses(TreeNode<Course> node) {
        if (node == null) {
            return 0;
        }
        return 1 + countCourses(node.left) + countCourses(node.right);
    }

    @Override
    public void load(File file) {
        if (!file.exists()) {
            System.out.println("File doesn't exist.");
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Course course = parseCourse(line);
                if (course != null) {
                    insert(course);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Course parseCourse(String data) {
        String[] properties = data.split(DataParser.PROPERTY_SEPARATOR);
        if (properties.length != 8) {
            return null;
        }
        String ccode = properties[0].trim();
        String scode = properties[1].trim();
        String sname = properties[2].trim();
        String semester = properties[3].trim();
        String year = properties[4].trim();
        int seats = Validation.parseInt(properties[5].trim());
        int registered = Validation.parseInt(properties[6].trim());
        double price = Validation.parseDouble(properties[7].trim());

        return new Course(ccode, scode, sname, semester, year, seats, registered, price);
    }

    @Override
    public void save(File file) {
        Path path = file.toPath();
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            savePostOrder(writer, root);
        } catch (IOException e) {
            throw new RuntimeException("Error saving courses to file", e);
        }
    }

    public Course getCourseDetailsFromUser(){
        System.out.println("Please enter the following course details:");
        System.out.print("Enter course code: ");
        String ccode = Validation.getString().toUpperCase();
        if (searchByCode(ccode) != null) {
            System.out.println("this course has been registered");
            return null;
        }
        System.out.print("Enter course short code: ");
        String scode = Validation.getString();

        System.out.print("Enter course name: ");
        String sname = Validation.getString();

        System.out.print("Enter semester: ");
        String semester = Validation.getString();

        System.out.print("Enter year: ");
        String year = Validation.getStringYear();

        int seats = Validation.getInteger("Enter seat: ", "Seat must be greater than 0", 0, Integer.MAX_VALUE);
        int registered = Validation.getInteger("Enter number of registered student for this course: ", "Registered number of student must be greater than 0 and lower than the number of seats.", 0, seats);
        double price = Validation.getDouble("Enter price of the course: ", "Price should be from 0 to 1000000 or in valid type.", 0, 1000000);

        return new Course(ccode, scode, sname, semester, year, seats, registered, price);
    }

    public void savePostOrder(BufferedWriter writer, TreeNode<Course> node) throws IOException {
        if (node == null) {
            return;
        }

        savePostOrder(writer, node.left);
        savePostOrder(writer, node.right);

        writer.write(node.data.toDataString());
        writer.newLine();
    }


    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void insert(Course value) {
        TreeNode<Course> newNode = new TreeNode<>(value);

        if (isEmpty()){
            root = newNode;
            return;
        }

        TreeNode<Course> current = root;
        TreeNode<Course> parent = null;

        while(current != null){

            parent = current;

            if(value.getCcode().equals(current.data.getCcode())){
                System.out.println("The course " + current.data + "is already in the list.");
                return;
            }

            if(value.getCcode().compareTo(current.data.getCcode()) < 0){
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if(value.getCcode().compareTo(parent.data.getCcode()) < 0){
            parent.left = newNode;
        } else{
            parent.right = newNode;
        }

        System.out.println("Add course " + value.getCcode() + " successfully.");
    }

    @Override
    public void preOrder(TreeNode<Course> node) {
        if(node == null){
            return;
        }
        display(node);
        preOrder(node.left);
        preOrder(node.right);
    }

    @Override
    public void inOrder(TreeNode<Course> node) {
        if(node == null) {
            return;
        }
        inOrder(node.left);
        display(node);
        inOrder(node.right);
    }

    @Override
    public void postOrder(TreeNode<Course> node) {
        if(node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        display(node);
    }

    @Override
    public TreeNode<Course> get(Course value) {
        TreeNode<Course> current = root;

        while(current != null){

            if(value.getCcode().equals(current.data.getCcode())){
                return current;
            }

            if(value.getCcode().compareTo(current.data.getCcode()) < 0){
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    @Override
    public TreeNode<Course> getParent(TreeNode<Course> node) {
        if (node == null || node == root) {
            return null;
        }

        TreeNode<Course> current = root;
        TreeNode<Course> parent = null;

        while (current != null) {
            if (node.data.getCcode().equals(current.data.getCcode())) {
                return parent;
            }

            parent = current;

            if (node.data.getCcode().compareTo(current.data.getCcode()) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    @Override
    public void breadth() {
        if(root == null){
            return;
        }

        CommonQueue<TreeNode<Course>> queue = new CommonQueue<>();
        queue.enqueue(root);

        while(!queue.isEmpty()){
            TreeNode<Course> current = queue.dequeue();
            display(current);

            if(current.left != null){
                queue.enqueue(current.left);
            }

            if(current.right != null){
                queue.enqueue(current.right);
            }
        }
    }

    public TreeNode<Course> findMaxNode(TreeNode<Course> node){
        while(node.right != null){
            node = node.right;
        }
        return node;
    }


    public TreeNode<Course> findMinNode(TreeNode<Course> node){
        while(node.left != null){
            node = node.left;
        }
        return node;
    }


    public TreeNode<Course> deleteByMergingRecursive(TreeNode<Course> node, Course value){
        if (node == null) {
            return null;
        }

        if (value.getCcode().compareTo(root.data.getCcode()) < 0) {
            // The value to delete is less, search in the left subtree
            root.left = deleteByMergingRecursive(root.left, value);
        } else if (value.getCcode().compareTo(root.data.getCcode()) > 0) {
            // The value to delete is greater, search in the right subtree
            root.right = deleteByMergingRecursive(root.right, value);
        } else {
            registerList.deleteRegistrationwithCourse(value.getCcode());  // Add Delete Registration with course
            // The value is found, we consider 3 possible ways
            if(node.left == null) return node.right; //Tree has only one right child
            if(node.right == null) return node.left; //Tree has only one left child

            /*
            Tree has both two child:
            + Step 1: We find the highest value on the left child
            + Step 2: We link this left child to the right child of the node that we want to delete
            + Step 3: Return newsubRoot to link to the parent of the original node.
            */

            TreeNode<Course> subRoot = node.left;
            TreeNode<Course> maxNode = findMaxNode(subRoot);
            maxNode.right = node.right;
            return subRoot;
        }

        return node;

    }


    public TreeNode<Course> deleteByCopyingRecursive(TreeNode<Course> node, Course value) {
        if (node == null) {
            return null;
        }

        if (value.getCcode().compareTo(root.data.getCcode()) < 0) {
            // The value to delete is less, search in the left subtree
            root.left = deleteByMergingRecursive(root.left, value);
        } else if (value.getCcode().compareTo(root.data.getCcode()) > 0) {
            // The value to delete is greater, search in the right subtree
            root.right = deleteByMergingRecursive(root.right, value);
        } else {
            registerList.deleteRegistrationwithCourse(value.getCcode()); // Add Delete Registration with course
            // The value is found, we consider 3 possible ways
            if(node.left == null) return node.right; //Tree has only one right child
            if(node.right == null) return node.left; //Tree has only one left child

            /*
            Tree has both two child:
            + Step 1: We find the highest value on the left child
            + Step 2: We copy the data of the highest node we just found to the node we want to delete
            + Step 3: We delete the position of the node we just copy
            */

            TreeNode<Course> subRoot = node.left;
            TreeNode<Course> maxNode = findMaxNode(subRoot);
            node.data = maxNode.data;
            node.left = deleteByCopyingRecursive(node.left, maxNode.data);

        }

        return node;

    }

    @Override
    public void deleteByCopying(Course data) {
        root = deleteByCopyingRecursive(root, data);
    }

    @Override
    public void deleteByMerging(Course data) {
        root = deleteByMergingRecursive(root, data);
    }

    @Override
    public void toInOrderArray(ArrayList<Course> array, TreeNode<Course> start) {
        if(start == null){
            return;
        }
        toInOrderArray(array, start.left);
        array.add(start.data);
        toInOrderArray(array, start.right);
    }

    @Override
    public void balance(ArrayList<Course> data, int start, int end) {
        if (start > end){
            return;
        }
        int mid = (start + end) / 2;
        insert(data.get(mid));
        balance(data, start, mid - 1);
        balance(data, mid + 1, end);
    }

    @Override
    public void balance() {
        if (isEmpty()){
            return;
        }
        ArrayList<Course> courseList = new ArrayList<>();
        toInOrderArray(courseList, root);
        clear();
        balance(courseList, 0, courseList.size()-1);

    }

    @Override
    public void display(TreeNode<Course> node) {
        if (node == null) {
            return;
        }

        System.out.printf(
                "%-10s | %-10s | %-30s | %-10s | %-10s | %-7d | %-12d | %.3f\n",
                node.data.getCcode(),
                node.data.getScode(),
                node.data.getSname(),
                node.data.getSemester(),
                node.data.getYear(),
                node.data.getSeats(),
                node.data.getRegistered(),
                node.data.getPrice()
        );
    }


    public void displayCourses(int mode) {
        if (this.root == null) {
            System.out.println("No course yet");
            return;
        }

        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        System.out.printf(
                "%-10s | %-10s | %-30s | %-10s | %-10s | %-7s | %-12s | %s\n",
                "CourseID", "SubjectID", "Subject Name", "Semester", "Year", "Seats", "Registered", "Price"
        );
        System.out.println("----------------------------------------------------------------------------------------------------------------------");

        if(mode == 1){
            preOrder(this.root);
        } else if(mode == 2){
            inOrder(this.root);
        } else if(mode == 3){
            postOrder(this.root);
        } else if(mode == 4) {
            breadth();
        } else {
            System.out.println("Nhìn lại số đi Dũng chan");
        }

        System.out.println("----------------------------------------------------------------------------------------------------------------------");
    }



    @Override
    public TreeNode<Course> searchByCode(String code){
        if(isEmpty()){
            return null;
        }

        Course tempCourse = new Course();
        tempCourse.setCcode(code);
        TreeNode<Course> foundCourse = get(tempCourse);

        if(foundCourse != null){
            System.out.println("Course Found: " + foundCourse.data);
            StudentTree findRegisterStudent = registerList.findRegisterStudentByCourse(code);
            if (findRegisterStudent != null){
                System.out.println("Students register this course: ");
                findRegisterStudent.displayStudents(4);
            } else{
                System.out.println("Course has no student.");
            }
            return foundCourse;
        } else{
            System.out.println("Course not found.");
            return null;
        }
    }

    @Override
    public CourseTree searchByName(String name){
        if(isEmpty()){
            return null;
        }

        CourseTree newTree = new CourseTree();

        CommonQueue<TreeNode<Course>> queue = new CommonQueue<>();
        queue.enqueue(root);

        while(!queue.isEmpty()){

            TreeNode<Course> current = queue.dequeue();

            if(current.data.getSname().contains(name)){
                newTree.insert(current.data);
            }

            if (current.left != null) {
                queue.enqueue(current.left);
            }
            if (current.right != null) {
                queue.enqueue(current.right);
            }
        }

        newTree.breadth();
        return newTree;
    }

    @Override
    public String toPreorderCodeString() {
        StringBuilder sb = new StringBuilder();
        preOrderCodeString(sb, root);
        return sb.toString();
    }

    private void preOrderCodeString(StringBuilder sb, TreeNode<Course> node) {
        if (node == null) {
            return;
        }
        sb.append(node.toString());
        preOrderCodeString(sb, node.left);
        preOrderCodeString(sb, node.right);
    }

    public boolean isBalanced() {
        return isBalanced(root) != -1;
    }

    private int isBalanced(TreeNode<Course> node) {
        if (node == null) {
            return 0; 
        }

        int leftHeight = isBalanced(node.left);
        if (leftHeight == -1) {
            return -1; 
        }

        int rightHeight = isBalanced(node.right);
        if (rightHeight == -1) {
            return -1; 
        }

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1; 
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }

}
