package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SimpleSetCheckPointTest {
    @Test
    void when4AddAndAddFirstThenFalse() {
        SimpleSetCheckPoint set = new SimpleSetCheckPoint();
        set.add("first");
        set.add("second");
        set.add("third");
        set.add("four");
        assertThat(set.add("first")).isFalse();
    }

}