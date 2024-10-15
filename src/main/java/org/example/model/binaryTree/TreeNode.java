package org.example.model.binaryTree;

import org.example.io.DataWriter;
import org.example.model.types.Course;

public class TreeNode<T> {
    public T data;
    public TreeNode<T> left;
    public TreeNode<T> right;

    public TreeNode() {}

    public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public TreeNode(T data) {
        this(data, null, null);
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "data=" + data +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    public String toDataString() {
        return data + DataWriter.PROPERTY_SEPARATOR + left + DataWriter.PROPERTY_SEPARATOR + right;
    }
}
