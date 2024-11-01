package org.example.model.linkedList;

public class CommonQueue<T> {
    private ListNode<T> head;
    private ListNode<T> tail;

    public boolean isEmpty() {
        return head == null;
    }

    public void enqueue(T value) {
        if (isEmpty()) {
            ListNode<T> p = new ListNode<>(value);// p.next = null
            head = p;// noi vao lien ket
            tail = p;
            return;
        }
        ListNode<T> p = new ListNode<>(value);// p.next = null
        tail.next = p;// noi vao lien ket
        tail = p;// tro vao cai tiep theo
    }

    public T dequeue() {
        if (isEmpty()) {
            return null;
        }
        ListNode<T> x = head;
        head = head.next;
        x.next = null;
        return x.data;
    }
}
