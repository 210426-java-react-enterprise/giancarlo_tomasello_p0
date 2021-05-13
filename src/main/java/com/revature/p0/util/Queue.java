package com.revature.p0.util;

/**
 * A Queue interface that adds two methods, poll() & peek() that allows you to get
 * the data at the front and back of a data structure
 * @param <T>
 */
public interface Queue<T> extends Collection<T> {
    T poll();
    T peek();
}
