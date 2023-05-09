package org.example.utils;

public class LinkedList<T> {
    Node<T> head;
    private int len = 0;

    public LinkedList() {
        this.head = null;
    }

    public void addNode(T data) {
        Node<T> temp = new Node<>(data);

        if (this.head == null) {
            head = temp;
        } else {
            Node<T> linkedListIterator = head;

            while (linkedListIterator.next != null) {
                linkedListIterator = linkedListIterator.next;
            }

            linkedListIterator.next = temp;
        }

        len++;
    }

    public void removeNode(T data) {
        Node<T> prev = new Node<>(null);
        prev.next = head;

        Node<T> next = head.next;
        Node<T> temp = head;

        boolean exists = false;

        if (head.data == data) {
            head = head.next;
            exists = true;
        }

        while (temp.next != null) {

            if (String.valueOf(temp.data).equals(
                    String.valueOf(data))) {
                prev.next = next;
                exists = true;
                break;
            }

            prev = temp;
            temp = temp.next;
            next = temp.next;
        }

        if (!exists && String.valueOf(temp.data).equals(
                String.valueOf(data))) {
            prev.next = null;
            exists = true;
        }

        if (exists) {
            len--;
        } else {
            System.out.println("Given value does not exist in the linked list");
        }
    }

    public void clearList() {
        head = null;
        len = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int getLength() {
        return len;
    }

    public String toString() {

        String S = "{ ";

        Node<T> temp = head;

        if (temp == null)
            return S + " }";

        while (temp.next != null) {
            S += String.valueOf(temp.data) + " -> ";
            temp = temp.next;
        }

        S += String.valueOf(temp.data);
        return S + " }";
    }

}

class Node<T> {
    T data;
    Node<T> next;

    Node(T data) {
        this.data = data;
        this.next = null;
    }
}
