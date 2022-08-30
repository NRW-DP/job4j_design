package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int changedCount = 0;
        int deletedCount = 0;
        Map<Integer, String> map = new HashMap<>();
        for (User i : current) {
            map.put(i.getId(), i.getName());
        }
        for (User j : previous) {
            if (map.get(j.getId()) == null) {
                deletedCount++;
            } else if (!map.get(j.getId()).equals(j.getName())) {
                changedCount++;
            }
        }
        int added = current.size() - previous.size() + deletedCount;
        return new Info(added, changedCount, deletedCount);
    }
}

