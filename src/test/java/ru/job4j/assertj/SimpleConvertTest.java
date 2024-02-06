package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void testToList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("apple", "orange", "banana");
        assertThat(list).hasSize(3)
                .contains("orange")
                .contains("banana", Index.atIndex(2))
                .containsAnyOf("grape", "apple", "kiwi")
                .doesNotContain("mango");
    }

    @Test
    void testToSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("cat", "dog", "mouse");
        assertThat(set).hasSize(3)
                .contains("cat")
                .contains("dog")
                .containsAnyOf("raccoon", "elephant", "mouse")
                .doesNotContain("fish");
    }

    @Test
    void testToMap1() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("1", "2", "3");
        assertThat(map).hasSize(3)
                .containsEntry("1", 0)
                .containsEntry("2", 1)
                .containsEntry("3", 2)
                .doesNotContainKey("10")
                .doesNotContainValue(4)
                .containsEntry("2", 1);
    }

    @Test
    void testToMap2() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("one", "two", "three");

        assertThat(map).hasSize(3)
                .containsEntry("one", 0)
                .containsEntry("two", 1)
                .containsEntry("three", 2)
                .doesNotContainEntry("four", 3);
    }

    @Test
    void testToMapIsEmpty() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap();
        assertThat(map).isEmpty();
    }
}