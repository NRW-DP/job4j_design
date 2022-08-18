package ru.job4j.map;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

/**
 * Класс описывает реализацию структуры данных HashMap
 *
 * @author - Ivan H.
 * @version 1.0
 */
public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (key == null && table[0] == null) {
            table[0] = new MapEntry<>(null, value);
            count++;
            modCount++;
            return true;
        }
        return false;
    }

    /**
     * Метод реализует хэш-функцию
     *
     * @param hashCode хэш-код
     * @return хэш
     */
    private int hash(int hashCode) {
        return Math.abs(hashCode % capacity);
    }

    /**
     * Метод определяет индекс для таблицы
     *
     * @param hash значение хэш-кода
     * @return индекс
     */
    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    /**
     * Метод расширяет таблицу.
     * Создаем временную таблицу и сохраняем в ней старую таблицу.
     * Старую таблицу заменяем новой в 2 раза больше.
     * Циклом идем по временной таблице. Если клетка таблицы не null, тогда
     * записываем её ключ и значение в новую таблицу через метод put(K key, V value)
     */
    private void expand() {
        MapEntry<K, V>[] tmp = table;
        capacity *= 2;
        count = 0;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> el : tmp) {
            if (el != null) {
                put(el.key, el.value);
                count++;
                modCount++;
            }
        }
    }


    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public boolean remove(K key) {
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleMap<?, ?> simpleMap = (SimpleMap<?, ?>) o;
        return capacity == simpleMap.capacity && count == simpleMap.count && modCount == simpleMap.modCount && Arrays.equals(table, simpleMap.table);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(capacity, count, modCount);
        result = 31 * result + Arrays.hashCode(table);
        return result;
    }
}