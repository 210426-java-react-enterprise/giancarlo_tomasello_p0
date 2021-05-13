package com.revature.p0.util;

import java.lang.reflect.Array;
import java.util.Iterator;

public class ArrayList<T> implements List<T>{

    private int size = 0;
    private int threshold = 8;
    private T[] elements  = (T[])new Object[10];


    /**
     * Returns and int that represents the size of the ArrayList
     * @return size
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns true if the ArrayList contains the specified value
     * Does not accept null values and throws an exception if called on an empty ArrayList
     *
     * @param data The item you are looking for in the ArrayList
     * @return bool True if it contains the data
     * @throws IllegalArgumentException Called when null data is used or if called on empty ArrayList
     */
    @Override
    public boolean contains(T data) throws IllegalArgumentException{
        if(data == null){
            throw new IllegalArgumentException("This Array List does not accept null values");
        } else if (size <= 0){
            throw new IllegalArgumentException("Calling Contains on an empty ArrayList");
        }

        for (int i = 0; i < size; i++) {
            if(elements[i] == data){
                return true;
            }
        }

        return false;
    }

    /**
     * Adds new data to the end of the ArrayList. Does not accept null data and will throw an exception
     * @param data The desired object to add to the ArrayList
     * @throws IllegalArgumentException The exception called when null data is used
     */
    @Override
    public void add(T data) throws IllegalArgumentException{

        if(data == null){
            throw new IllegalArgumentException("This Array List does not accept null values");
        }
        elements[size] = data;
        size++;

        if(size == threshold){
            T[] newArr = (T[]) new Object[elements.length*2];
            for (int i = 0; i < elements.length; i++) {
                newArr[i] = elements[i];
            }
            elements = newArr.clone();
            threshold = (int) ((int) elements.length*0.8);
        }
    }

    /**
     * Finds, returns, and removes the specified object from the ArrayList, if data not found return null.
     * @param data The object that is to be removed from the list
     * @return data The object that was removed. Wil be null if data not found
     * @throws IllegalArgumentException Throws an exception when trying to remove null data from the ArrayList
     */
    @Override
    public T remove(T data) throws IllegalArgumentException{

        if(data == null){
            throw new IllegalArgumentException("This Array List does not accept null values");
        }

        if(!contains(data)){
            return null;
        }

        int emptyIndex = -1;
        for (int i = 0; i < size; i++) {
            if(elements[i] == data){
                emptyIndex = i;
                break;
            }
        }

        //return null if nothing was removed
        if(emptyIndex == -1){
            return null;
        }

        for(int i = emptyIndex; i < size-1; i++){
            elements[i] = elements[i+1];
        }

        size--;
        //Return the data to tell it was removed
        return data;
    }

    /**
     * Deletes data at specified index. If index is out of bounds throw an error
     *
     * @param index The postion of the object to be removed
     * @return bool Returns true if the object was successfully removed
     * @throws IllegalArgumentException Returns an exception when out of bounds data used
     */
    public boolean remove(int index) throws IllegalArgumentException{
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("This provided index would be out of bounds");
        }

        for (int i = index; i < size-1; i++) {
            elements[i] = elements[i+1];
        }
        size--;
        return true;
    }

    /**
     * Get and return data at a specific index, throws an exception when out of bounds
     * @param index The position that you want to remove data from
     * @return data Return the data at the specified position
     * @throws IllegalArgumentException Throws this exception when index is out of bounds
     */
    @Override
    public T get(int index) throws IllegalArgumentException {
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("This provided index would be out of bounds");
        }

        return elements[index];
    }

}
