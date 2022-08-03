package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

/**
 * Класс описывает реализацию коллекции Set на основе динамического массива
 * Коллекция не содержит дубликаты
 * @author Ivan H.
 * @version 1.0
 */
public class SimpleSet<T> implements Set<T> {
    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    /**
     * Меотод добавляет значение в коллекцию,
     * если такого значение там нет
     * @param value - значение
     * @return true or false
     */
    @Override
    public boolean add(T value) {
        boolean check = !contains(value);
        if (check) {
            set.add(value);
        }
        return check;
    }

    /**
     * Метод проверяет наличие элемента в коллекции
     * @param value - значение
     * @return - true or false
     */
    @Override
    public boolean contains(T value) {
        boolean check = false;
        for (T x : set) {
            if (Objects.equals(x, value)) {
                check = true;
                break;
            }
        }
        return check;
    }


    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
