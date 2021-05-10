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
    public boolean contains(T data) {
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

    @Override
    public T remove(T data) {
        return null;
    }

    @Override
    public T get(int index) {
        return elements[index];
    }
}