package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {

    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (row >= data.length) {
            return false;
        }
        return column < data[row].length || row != data.length - 1;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int element = data[row][column];
        column++;
        while (row < data.length && column >= data[row].length) {
            column = 0;
            row++;
        }
        return element;
    }
}
