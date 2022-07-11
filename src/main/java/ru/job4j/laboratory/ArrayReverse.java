package ru.job4j.laboratory;

import java.util.Arrays;

public class ArrayReverse {
    public static void main(String[] args) {
        int[] array = {2, 4, 5, 11, 99, 1};
        for (int i = 0; i < array.length / 2; i++) {
            int tmp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = tmp;
        }
        System.out.print("Array after reverse: ");
        System.out.println(Arrays.toString(array));
    }
}
