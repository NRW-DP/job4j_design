package ru.job4j.iterator;

import java.util.*;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        int index = 0;
        while (source.hasNext()) {
            if (index == nodes.size()) {
                index = 0;
            }
            Integer value = source.next();
            nodes.get(index).add(value);
            index++;
        }
    }
}
