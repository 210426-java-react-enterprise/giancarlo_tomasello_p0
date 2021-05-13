package com.revature.p0.util;

/**
 * A custom list Interface that adds the get() method to
 * a created data structure
 * @param <T>
 */
public interface List<T> extends Collection<T>{
    T get(int index);
}
