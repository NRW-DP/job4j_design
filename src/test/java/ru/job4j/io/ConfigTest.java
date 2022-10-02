package ru.job4j.io;

import org.junit.jupiter.api.Test;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("1"), is("one"));
        assertThat(config.value("2"), is("two=two"));
        assertThat(config.value("3"), is("three="));
    }
}