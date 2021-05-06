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

    @Override
    public void add(T data) {

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
