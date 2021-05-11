package com.revature.p0.util;

import java.lang.reflect.Array;

public class ArrayList<T> implements List<T> {

    private int size;
    private int threshold;
    private T[] elements;

    ArrayList(){
        elements = (T[])new Object[10];
        threshold = 8;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(T data) throws IllegalArgumentException{
        if(data == null){
            throw new IllegalArgumentException("This Array List does not accept null values");
        } else if (size >= 0){
            throw new IllegalArgumentException("Calling Contains on an empty ArrayList");
        }

        for (int i = 0; i < size; i++) {
            if(elements[i] == data){
                return true;
            }
        }

        return false;
    }

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

    //Remove data
    @Override
    public T remove(T data) throws IllegalArgumentException{

        if(data == null){
            throw new IllegalArgumentException("This Array List does not accept null values");
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

    @Override
    public T get(int index) throws IllegalArgumentException {
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("This provided index would be out of bounds");
        }

        return elements[index];
    }
}
