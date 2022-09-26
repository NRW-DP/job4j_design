package ru.job4j.laboratory;

public class StringBuilderTest {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("Hello");
        System.out.println(sb);
        sb.append(" my");
        sb.append(" friend.");
        System.out.println(sb);
    }
}
