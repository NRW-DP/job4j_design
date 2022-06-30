package ru.job4j.generic;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс описывает универсальное хранилище
 *
 * @author Ivan H.
 * @version 1.0
 */
public final class MemStore<T extends Base> implements Store<T> {
    private final Map<String, T> storage = new HashMap<>();

    /**
     * Метод добавляет в хранилище объект типа T, при этом метод ничего не возвращает
     *
     * @param model объект типа Т
     */
    @Override
    public void add(T model) {
        storage.putIfAbsent(model.getId(), model);
    }

    /**
     * метод выполняет замену объекта по id, на объект который передается в параметрах метода
     * и возвращает true, если замена выполнена успешно;
     *
     * @param id    объект типа String
     * @param model объект типа Т
     * @return true or false
     */
    @Override
    public boolean replace(String id, T model) {
        return storage.replace(id, model) != null;
    }

    /**
     * метод удаляет пару ключ-значение по id, который передается в метод,
     * и возвращает true, если удаление выполнено успешно;
     *
     * @param id ключ-объект типа String
     * @return true or false
     */
    @Override
    public boolean delete(String id) {
        return storage.remove(id) != null;
    }

    /**
     * метод возвращает объект по ключу
     *
     * @param id объект типа String (ключ)
     * @return объект типа T или null
     */
    @Override
    public T findById(String id) {
        return storage.get(id);
    }
}
