package ru.job4j.collection;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class ForwardLinkedTest {
    private ForwardLinked<Integer> linked;

    @BeforeEach
    public void init() {
        linked = new ForwardLinked<>();
    }

    @Test
    public void whenSize0ThenReturnFalse() {
        assertThat(linked.revert()).isFalse();
    }

    @Test
    public void whenSize1ThenReturnFalse() {
        linked.add(1);
        assertThat(linked.revert()).isFalse();
    }

    @Test
    public void whenAddAndRevertTrue() {
        linked.add(1);
        linked.add(2);
        linked.add(3);
        linked.add(4);
        assertThat(linked).containsSequence(1, 2, 3, 4);
        assertThat(linked.revert()).isTrue();
        assertThat(linked).containsSequence(4, 3, 2, 1);
    }
}