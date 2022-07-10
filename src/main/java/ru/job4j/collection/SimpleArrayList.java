package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {
    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    private void resizeArray() {
        if (container.length == size && container.length != 0) {
            container = Arrays.copyOf(container, container.length * 2);
        }
        if (container.length == size && container.length == 0) {
            container = Arrays.copyOf(container, container.length + 10);
        }
    }

    @Override
    public void add(T value) {
        modCount++;
        resizeArray();
        container[size++] = value;
    }

    @Override
    public T set(int index, T newValue) {
        T rsl = get(index);
        container[index] = newValue;
        return rsl;
    }

    @Override
    public T remove(int index) {
        modCount++;
        T removeElement = get(index);
        System.arraycopy(container, index + 1, container, index, size - index - 1);
        container[container.length - 1] = null;
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
        return new Iterator<>() {
            private int cursor;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[cursor++];
            }
        };
    }
}
