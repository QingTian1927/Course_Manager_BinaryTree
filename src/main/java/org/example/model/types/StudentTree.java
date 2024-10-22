package org.example.model.types;

import org.example.model.binaryTree.CommonTreeInterface;
import org.example.model.binaryTree.TreeNode;
import org.example.model.linkedList.CommonQueue;
import org.example.util.Validation;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class StudentTree implements CommonTreeInterface<Student> {

    private TreeNode<Student> root;
    private final RegisterList registerList;

    public StudentTree(RegisterList registerList) {
        this.root = null;
        this.registerList = registerList;
    }

    public StudentTree() {
        root = null;
        registerList = new RegisterList();
    }

    public TreeNode<Student> getRoot() {
        return root;
    }

    @Override
    public void clear() {
        this.root = null;
    }

    @Override
    public boolean isEmpty() {
        return this.root == null;
    }

    @Override
    public void insert(Student value) {
        if (isEmpty()) {
            this.root = new TreeNode<Student>(value);
            return;
        }

        String insertCode = value.getScode();
        TreeNode<Student> cur = root;
        TreeNode<Student> parent = null;

        while (cur != null) {
            String currentStudent = cur.data.getScode();
            if (currentStudent.equals(insertCode)) {
                System.out.println("Student code has existed");
                return;
            }
            parent = cur;
            if (currentStudent.compareTo(insertCode) < 0) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }

        TreeNode<Student> newStudent = new TreeNode<Student>(value);
        if (parent.data.getScode().compareTo(insertCode) > 0) {
            parent.left = newStudent;
        } else {
            parent.right = newStudent;
        }
    }

    @Override
    public void preOrder(TreeNode<Student> node) {
        if(node == null){
            return;
        }
        System.out.println(node.data.toDataString());
        preOrder(node.left);
        preOrder(node.right);
    }

    @Override
    public void inOrder(TreeNode<Student> node) {
        if(node == null){
            return;
        }
        inOrder(node.left);
        System.out.println(node.data.toDataString());
        inOrder(node.right);
    }

    @Override
    public void postOrder(TreeNode<Student> node) {
        if(node == null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.data.toDataString());
    }

    @Override
    public void breadth() {
        if(isEmpty()){
            return;
        }
        CommonQueue<TreeNode<Student>> queue = new CommonQueue<TreeNode<Student>>();
        queue.enqueue(root);
        TreeNode<Student> cur;
        while(!queue.isEmpty()){
            cur = queue.dequeue();
            display(cur);
            if(cur.left != null){
                queue.enqueue(cur.left);
            }
            if(cur.right != null){
                queue.enqueue(cur.right);
            }
        }
    }


    @Override
    public TreeNode<Student> get(Student value) {
        TreeNode<Student> current = root;

        while(current != null){

            if(value.getScode().equals(current.data.getScode())){
                return current;
            }

            if(value.getScode().compareTo(current.data.getScode()) < 0){
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;
    }

    public TreeNode<Student> get(String code) {
        TreeNode<Student> current = root;

        while(current != null){

            if(code.equals(current.data.getScode())){
                return current;
            }

            if(code.compareTo(current.data.getScode()) < 0){
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;
    }

    @Override
    public TreeNode<Student> getParent(TreeNode<Student> node) {
        TreeNode<Student> cur = root;
        TreeNode<Student> parent = null;

        while(cur != null){
            if(cur == node){
                break;
            }
            parent = cur;
            if(cur.data.getScode().compareTo(node.data.getScode()) < 0){
                cur = cur.right;
            }else{
                cur = cur.left;
            }
        }
        return parent;
    }

    @Override
    public void deleteByMerging(Student data) {

    }

    @Override
    public void deleteByCopying(Student data) {

    }

    @Override
    public TreeNode<Student> searchByCode(String code) {
        if(isEmpty()){
            return null;
        }
        CommonQueue<TreeNode<Student>> queue = new CommonQueue<TreeNode<Student>>();
        queue.enqueue(root);
        TreeNode<Student> cur;
        while(!queue.isEmpty()){
            cur = queue.dequeue();
            if(cur.data.getScode().equals(code)){
                return cur;
            }
            if(cur.left != null){
                queue.enqueue(cur.left);
            }
            if(cur.right != null){
                queue.enqueue(cur.right);
            }
        }
        System.out.println("Student not found");
        return null;
    }

    @Override
    public StudentTree searchByName(String name) {
        StudentTree foundStudent = new StudentTree();
        CommonQueue<TreeNode<Student>> queue = new CommonQueue<TreeNode<Student>>();
        queue.enqueue(root);
        TreeNode<Student> cur;
        while(!queue.isEmpty()){
            cur = queue.dequeue();
            if(cur.data.getName().contains(name)){
                foundStudent.insert(cur.data);
            }
            if(cur.left != null){
                queue.enqueue(cur.left);
            }
            if(cur.right != null){
                queue.enqueue(cur.right);
            }
        }
        return foundStudent;
    }

    public CourseTree findRegisterCourse(String scode){
        CommonQueue<TreeNode<Student>> queue = new CommonQueue<TreeNode<Student>>();
        queue.enqueue(root);
        TreeNode<Student> cur;
        while(!queue.isEmpty()){
            cur = queue.dequeue();
            if(cur.data.getScode().contains(scode)){
                return registerList.findRegisterCourseByStudent(scode);
            }
            if(cur.left != null){
                queue.enqueue(cur.left);
            }
            if(cur.right != null){
                queue.enqueue(cur.right);
            }
        }
        System.out.println("No course found");
        return null;
    }

    @Override
    public void toInOrderArray(ArrayList<Student> array, TreeNode<Student> start) {

    }

    @Override
    public void balance(ArrayList<Student> data, int start, int end) {

    }

    @Override
    public void balance() {

    }

    @Override
    public void display(TreeNode<Student> node) {
        if (node == null) {
            return;
        }

        System.out.printf(
                "%-15s | %-40s | %s\n",
                node.data.getScode(),
                node.data.getName(),
                node.data.getByear()
        );
    }

    public void displayStudents(int mode) {
        if (this.root == null) {
            System.out.println("No Student yet");
            return;
        }

        System.out.println("---------------------------------------------------------------------------------");
        System.out.printf(
                "%-15s | %-40s | %s\n",
                "StudentID", "Student's Name", "Student's Birth Year"
        );
        System.out.println("---------------------------------------------------------------------------------");

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
    public void load(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] properties = line.split(";");
                if (properties.length == 3) {
                    String scode = properties[0].trim();
                    String name = properties[1].trim();
                    int byear = Integer.parseInt(properties[2].trim());

                    Student student = new Student(scode, name, byear);
                    insert(student);
                } else {
                    System.out.println("Invalid data format: " + line);
                }
            }
            System.out.println("Successfully loaded the student list from the file.");
        } catch (IOException e) {
            System.out.println("[FATAL] Failed to read students.txt");
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void postOrder(TreeNode<Student> node, BufferedWriter writer) throws IOException {
        if(node == null){
            return;
        }
        postOrder(node.left, writer);
        postOrder(node.right, writer);
        writer.write(node.data.toDataString());
        writer.newLine();
    }

    @Override
    public void save(File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            postOrder(root, writer);
            System.out.println("Successfully saved the student list to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the file.");
        }
    }


    @Override
    public String toPreorderCodeString() {
        return "";
    }
}
