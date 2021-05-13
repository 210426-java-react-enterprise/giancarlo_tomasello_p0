package com.revature.p0.util;

/**
 * Custom collection Interface to use in the creation of
 * Data structures
 *
 *
 * @param <T> The data type you want the collection to be of
 */
public interface Collection<T> {
    int size();
    boolean contains(T data);
    void add(T data);
    T remove(T data);

}
