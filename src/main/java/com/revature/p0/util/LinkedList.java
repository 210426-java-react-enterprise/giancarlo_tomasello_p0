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
        return null;
    }

    @Override
    public T poll() {
        return null;
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
