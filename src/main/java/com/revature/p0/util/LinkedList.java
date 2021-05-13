package com.revature.p0.util;

//A doubly Linked List
public class LinkedList<T> implements List<T>, Queue<T> {

    private int size;
    private Node<T> head;
    private Node<T> tail;

    /**
     * Returns the size of the Linked List
     * @return size An int that represents the current size of the Linked List
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns true if the Link List contains a specific object
     * ~Currently not implemented~
     * @param data The specific data you are looking for
     * @return Always returns false
     */
    @Override
    public boolean contains(T data) {
        return false;
    }

    /**
     * Adds a new object to the end of the Linked List. Does not accept null objects
     * @param data The new object that you want to add to the end of the Linked List
     * @throws IllegalArgumentException Thrown when given a null object
     */

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

    /**
     * Removes the specified data from the Linked List
     * ~Currently not implements~
     * @param data The object to be removed from the list
     * @return Always returns null
     */

    @Override
    public T remove(T data) {
        return null;
    }

    /**
     * Returns the object at a specific index in the Linked List
     * @param index The position you want to get an object from
     * @return The object at the specified index
     * @throws IllegalArgumentException Thrown when accessing an out of bound index
     */
    @Override
    public T get(int index) throws IllegalArgumentException{

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

    /**
     * Returns and removes the data at the head of the LinkedList
     * @return The data at the front of the list
     */
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

    /**
     * Gets the data at the front of the list without removing it
     * @return The data at the front of the list
     */
    @Override
    public T peek() {
        if(head == null){
            return null;
        }

        return head.data;

    }

    /**
     * A Node class the will be used solely in the implimentation of the Linked List Class
     * Holds data, a ref to the next node, and a ref to the prev node
     * @param <T> The data type you want the Linked List to hold
     */
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
