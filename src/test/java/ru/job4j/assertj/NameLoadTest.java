package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("collection contains no data");
    }

    @Test
    void parseEmptyArrayThrowsException() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Names array is empty");
    }

    @Test
    void parseInvalidFormatThrowsException() {
        NameLoad nameLoad = new NameLoad();
        String invalidInput = "key:value";
        assertThatThrownBy(() -> nameLoad.parse(invalidInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: key:value does not contain the symbol '='");
    }

    @Test
    void parseMissingKeyThrowsException() {
        NameLoad nameLoad = new NameLoad();
        String invalidInput = "=value";
        assertThatThrownBy(() -> nameLoad.parse(invalidInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: =value does not contain a key");
    }

    @Test
    void parseMissingValueThrowsException() {
        NameLoad nameLoad = new NameLoad();
        String invalidInput = "key=";
        assertThatThrownBy(() -> nameLoad.parse(invalidInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: key= does not contain a value");
    }
}