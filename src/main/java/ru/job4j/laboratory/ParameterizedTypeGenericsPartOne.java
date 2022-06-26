package ru.job4j.laboratory;

/**
 * При создании обьекта класса Info указавыем <ТИП> и он заменил в классе-шаблоне Info <T>
 * info - String
 * info2  - Integer
 * Параметр T в угловых скобках называется универсальным параметром, так как вместо него можно подставить любой тип
 */
public class ParameterizedTypeGenericsPartOne {
    public static void main(String[] args) {
        Info<String> info = new Info<>("privet");
        System.out.println(info);
        String s = info.getValue();


        Info<Integer> info2 = new Info<>(17);
        System.out.println(info2);
        Integer i = info2.getValue();
    }
}

class Info<T> {
    /**
     * переменная value не может быть статичной так как принадлежит классу, то есть она
     * является общей для всех обьектов класса и java не понимает какой тип подставлять String / Integer и т.д.
     */
    private T value;

    public Info(T value) {
        this.value = value;
    }
    public String toString() {
     return "{[ " + value + " ]}";
    }

    public T getValue() {
        return value;
    }
}