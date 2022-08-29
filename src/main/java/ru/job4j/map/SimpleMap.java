package ru.job4j.map;

import java.util.*;

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
/**

 */
    @Override
    public boolean put(K key, V value) {
        if ((float) count / capacity >= LOAD_FACTOR) {
            expand();
            count = 0;
            modCount = 0;
        }
        boolean rls = false;
        int i = checkKey(key);
        if (table[i] == null) {
            table[i] = new MapEntry<>(key, value);
            count++;
            modCount++;
            rls = true;
        }
        return rls;
    }

    /**
     * Метод реализует хэш-функцию
     *
     * @param hashCode хэш-код
     * @return хэш
     */
    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
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
     * Метод проверяет значение ключа на null
     *
     * @param key ключ
     * @return 0 или значение индекса таблицы
     */
    private int checkKey(K key) {
        return key == null ? 0 : indexFor(hash(key.hashCode()));
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
        table = new MapEntry[capacity];
        for (MapEntry<K, V> el : tmp) {
            if (el != null) {
                put(el.key, el.value);
            }
        }
    }


    @Override
    public V get(K key) {
        int index = checkKey(key);
        if (table[index] != null
                && (key.hashCode() != table[index].key.hashCode())
                || key.equals(table[index].key)) {
            return table[index].value;
        }
        return null;
    }

    /**
     * Метод удаляет значение по ключу.
     * Определяем индекс по ключу.
     * Если ячейка !null и ключи равны по equals(),
     * тогда удаляем значение, иначе false.
     *
     * @param key ключ
     * @return true, если удалил
     */
    @Override
    public boolean remove(K key) {
        boolean rls = false;
        int index = checkKey(key);
        if (table[index] != null
                && (key.hashCode() != table[index].key.hashCode())
                || key.equals(table[index].key)) {
            table[index] = null;
            rls = true;
            count--;
            modCount++;
        }
        return rls;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            int cursor = 0;
            int number = modCount;


            @Override
            public boolean hasNext() {
                if (modCount != number) {
                    throw new ConcurrentModificationException();
                }
                while (table.length > cursor && table[cursor] == null) {
                    cursor++;
                }
                return table.length > cursor;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[cursor++].key;
            }
        };

    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }
}