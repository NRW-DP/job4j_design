package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArrayList<T> implements SimpleList<T> {
    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    public void resizeArray() {
        if (container.length == size) {
            Object[] newArray = new Object[container.length * 2];
            System.arraycopy(container, 0, newArray, 0, size);
            container = (T[]) newArray;
        }
    }

    @Override
    public void add(T value) {
        resizeArray();
        container[size] = value;
        size++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        container[index] = newValue;
        return newValue;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T removeElement = container[index];
        System.arraycopy(container, index + 1, container, index, size - index - 1);
        size--;
        return removeElement;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return null;
            }

        };
    }
}
