package ru.job4j.iterator;

import java.util.*;
import java.util.function.Predicate;

/**
 * Класс описывает работу интерфейса ListIterator<E>
 * @author Ivan H
 * @version 1.0
 */
public class ListUtils {
    /**
     * Метод вставляет значение перед индексом
     * Проверяем индекс, создаем листИтератор.
     * Пока есть следующий элемент:
     * если индекс следующего элемента == индексу,
     * тогда добавляем значение
     *
     * @param list - список
     * @param index - индекс
     * @param value - значение
     */
    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.nextIndex() == index) {
                iterator.add(value);
                break;
            }
            iterator.next();
        }
    }

    /**
     * Метод вставляет значение в список после индекса.
     * Проверяем индекс, создаем листИтератор.
     * Пока есть следующий элемент:
     * если индекс следующего элемента == индексу
     * тогда переводим курсор далее
     * и добавляем значение
     *
     * @param list - список
     * @param index - индекс
     * @param value - значение
     */
    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.nextIndex() == index) {
                iterator.next();
                iterator.add(value);
                break;
            }
            iterator.next();
        }
    }

    /**
     * Метод удаляет все элементы по условию Предиката
     * Создаем листИтератор
     * Пока есть следующий элемент:
     * проверяет условие и удаляем элемент
     * который соответсвует этому условию
     *
     * @param list - список
     * @param filter - условие Predicate
     */
    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (filter.test(iterator.next())) {
                iterator.remove();
            }
        }
    }

    /**
     * Метод заменяет все элементы, которые удовлетворяют предикату.
     * Пока есть следующий элемент:
     * проверяем условие и заменяем элементы,
     * которые ему соответствуют
     *
     * @param list - лист
     * @param filter - условие
     * @param value - значение , на которое заменяется
     * @param <T> - тип
     */
    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        Objects.requireNonNull(value);
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (filter.test(iterator.next())) {
                iterator.set(value);
            }
        }
    }

    /**
     * Метод удаляет все элементы из списка elements
     * Создаем листИтератор
     * Пока существует следующий элемент:
     * проверяет условие и удаляем все элементы элемент
     *
     * @param list - список
     * @param elements - список elements
     */
    public static <T> void removeAll(List<T> list, List<T> elements) {
        Objects.requireNonNull(elements);
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (elements.contains(iterator.next())) {
                iterator.remove();
            }
        }
    }

}
