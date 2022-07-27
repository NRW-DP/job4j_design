package ru.job4j.collection;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinkedTest {
    private ForwardLinked<Integer> linked;

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteFirst() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.deleteFirst();
        linked.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteEmptyLinked() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.deleteFirst();
    }

    @Test
    public void whenMultiDelete() {
       ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        assertThat(linked.deleteFirst(), is(1));
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(2));
    }

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