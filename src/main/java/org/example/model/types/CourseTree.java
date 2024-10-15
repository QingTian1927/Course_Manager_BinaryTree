package org.example.model.types;

import org.example.model.binaryTree.CommonTreeInterface;
import org.example.model.binaryTree.TreeNode;
import org.example.model.linkedList.CommonQueue;
import sun.reflect.generics.tree.Tree;

import java.io.File;
import java.util.ArrayList;

public class CourseTree implements CommonTreeInterface<Course> {
    private TreeNode<Course> root;

    @Override
    public void load(File file) {
        // TODO: Implement loading courses from a file
    }

    @Override
    public void save(File file) {
        // TODO: Implement saving courses to a file
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
                System.out.println("The course is already in the list.");
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

        System.out.println("Add course successfully.");
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
        preOrder(node.left);
        display(node);
        preOrder(node.right);
    }

    @Override
    public void postOrder(TreeNode<Course> node) {
        if(node == null) {
            return;
        }
        preOrder(node.left);
        preOrder(node.right);
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
        if(isEmpty()){
            return null;
        }

        if (value.getCcode().compareTo(root.data.getCcode()) < 0) {
            // The value to delete is less, search in the left subtree
            root.left = deleteByMergingRecursive(root.left, value);
        } else if (value.getCcode().compareTo(root.data.getCcode()) > 0) {
            // The value to delete is greater, search in the right subtree
            root.right = deleteByMergingRecursive(root.right, value);
        } else {
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
        if(isEmpty()){
            return null;
        }

        if (value.getCcode().compareTo(root.data.getCcode()) < 0) {
            // The value to delete is less, search in the left subtree
            root.left = deleteByMergingRecursive(root.left, value);
        } else if (value.getCcode().compareTo(root.data.getCcode()) > 0) {
            // The value to delete is greater, search in the right subtree
            root.right = deleteByMergingRecursive(root.right, value);
        } else {
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
        // TODO: Implement deletion by copying
    }

    @Override
    public void deleteByMerging(Course data) {
        // TODO: Implement deletion by merging

    }

    @Override
    public void toInOrderArray(ArrayList<Course> array, TreeNode<Course> start) {
        // TODO: Convert the tree to an in-order array
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
        System.out.println(node.data);
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
            return foundCourse;
        } else{
            System.out.println("Course not found.");
            return  null;
        }
    }

    @Override
    public TreeNode<Course> searchByName(String name){
        if(isEmpty()){
            return null;
        }

        CommonQueue<TreeNode<Course>> queue = new CommonQueue<>();
        queue.enqueue(root);

        while(!queue.isEmpty()){

            TreeNode<Course> current = queue.dequeue();

            if(current.data.getSname().contains(name)){
                System.out.println("Subject Name found: " + current.data);
                return current;
            }

            if (current.left != null) {
                queue.enqueue(current.left);
            }
            if (current.right != null) {
                queue.enqueue(current.right);
            }
        }

        System.out.println("Subject Name not found.");
        return null;
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
}
