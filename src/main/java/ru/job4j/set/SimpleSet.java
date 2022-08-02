package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;

public class SimpleSet<T> implements Set<T> {
    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        boolean check = contains(value);
        if (!check) {
            set.add(value);
        }
        return check;
    }

    @Override
    public boolean contains(T value) {
        for (T x : set) {
            return x.equals(value);
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
