package com.revature.p0.util;

public class LinkedList<T> implements List<T>, Queue<T> {

    private int size;
    private Node<T> head;
    private Node<T> tail;

    //Returns the size of the LinkedList
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(T data) {
        return false;
    }

    //Add a new element to the end of the array
    @Override
    public void add(T data) throws IllegalArgumentException {

            if(data == null){
                System.out.println("throw exception");
                throw new IllegalArgumentException("This linked list does not accept null values");
            }

            Node<T> newNode = new Node<T>(data);
            if(head == null){
                tail = head = newNode; //Sets both the head and tail equal to the newly created node
            } else {
                tail.nextNode = newNode;
                newNode.prevNode = tail;
                tail = newNode;
            }

            size++;
    }

    @Override
    public T remove(T data) {
        return null;
    }

    @Override
    public T get(int index) {

        if (index < 0 || index > size) {
            throw new IllegalArgumentException("The provided index would be out of bounds.");
        }

        Node<T> runner = head;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                return runner.data;
            }
            runner = runner.nextNode;
        }

        return null;
    }

    @Override
    public T poll() {

        if (head == null) {
            return null;
        }

        T soughtData = head.data;
        head = head.nextNode;

        if (head != null) {
            head.prevNode = null;
        } else {
            tail = null;
        }

        size--;

        return soughtData;

    }

    @Override
    public T peek() {
        return null;
    }

    private static class Node<T>{

        T data;
        Node<T> nextNode;
        Node<T> prevNode;

        Node(T data){
            this.data = data;
        }

        Node(T data, Node<T> nextNode, Node<T> prevNode){
            this.data = data;
            this.nextNode = nextNode;
            this.prevNode = prevNode;
        }
    }

}
