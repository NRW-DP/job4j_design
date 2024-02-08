package ru.job4j.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CyclicIterator<T> implements Iterator<T> {

    private final List<T> data;
    private int currentIndex;


    public CyclicIterator(List<T> data) {
        this.data = data;
        this.currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return !data.isEmpty() && currentIndex < data.size();
    }

    @Override
    public T next() {
        if (data.isEmpty()) {
            throw new NoSuchElementException("Collection is empty!!!");
        }
        T result = data.get(currentIndex);
        currentIndex = (currentIndex + 1) % data.size();
        return result;
    }
}