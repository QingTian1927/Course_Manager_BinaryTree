package org.example.model.linkedList;

public class ListNode<T> {
    public T data;
    protected ListNode<T> next;

    public ListNode() {}

    public ListNode(T data, ListNode<T> next) {
        this.data = data;
        this.next = next;
    }

    public ListNode(T data) {
        this(data, null);
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "data=" + data +
                ", next=" + next +
                '}';
    }

    public String toDataString() {
        return data + ", " + next + "\n";
    }
}
