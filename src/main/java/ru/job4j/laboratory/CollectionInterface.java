package ru.job4j.laboratory;

import java.util.*;

public class CollectionInterface {
    public static void main(String[] args) {
        Set<Integer> set = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        });
        set.add(1);
        set.add(2);
        set.add(1);
        System.out.println(set);
    }
}
    /*
    Интерфейс Set<E> имеет три реализации: HashSet, LinkedHashSet и TreeSet.
    В обычном HashSet - (Set) - порядок не гарантируется , потому что добавляем по хешкоду куда попало в коллекцию-
    уникальность элементов обеспечивается методом equals / hashcode
    Изначально вычисляется хешкод , потом бросаем элемент в нашу коллекцию , если хешкод разный
    тогда элемент уникальный , добалвяет новый элемент если одинаковый , тогда сравнивает по equals
    А в Treeset - порядок гарантируется сортировкой -  (SortedSet) уникальность и порядок элементов обеспечивается с помощью метода compare()
    ( equals вообще не используется в Treeset)

    TreeSet --(implements)--> NavigableSet ---(extends)--> SortedSet ---(extends)--> Set

    NavigableSet - основ. методы - lower / higher / headSet / tailSet / descendingSet  - основные методы для навигации
    по сету
    LinkedHashSet - используют когда нужен порядок вставки сохранить
     */

