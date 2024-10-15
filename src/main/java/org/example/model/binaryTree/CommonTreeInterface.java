package org.example.model.binaryTree;

import java.io.File;
import java.util.ArrayList;

public interface CommonTreeInterface<T> {
    void clear();
    boolean isEmpty();
    void insert(T value);

    void preOrder(TreeNode<T> node);
    void inOrder(TreeNode<T> node);
    void postOrder(TreeNode<T> node);
    void breadth();

    TreeNode<T> get (T data);
    TreeNode<T> getParent(TreeNode<T> node);

    void deleteByMerging(T data);
    void deleteByCopying(T data);
    // New method for searching purposes
    TreeNode<T> searchByCode(String code); // Search by course or student code
    TreeNode<T> searchByName(String name);


    void toInOrderArray(ArrayList<T> array, TreeNode<T> start);
    void balance(ArrayList<T> data, int start, int end);
    void balance();

    void display(TreeNode<T> node);
    void load(File file);
    void save(File file);

    String toPreorderCodeString();
}
