package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/*
 * Принимает массив аргументов который передает в карту и разбивает на ключ / значение;
 */
public class ArgsName {
    /*
     * @values - хранит ключ / значение которые были переданы из массива args;
     */
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Key not exist");
        }
        return values.get(key);
    }

    private void checkString(String s) throws IllegalArgumentException {
        if (Pattern.matches("-\\w++=+\\S+", s)) {
            return;
        }
        throw new IllegalArgumentException("Enter the correct value");
    }

    /*
     * @param args - массив аргументов
     * @values - объект Map в который передаем значения из массива
     */
    private void parse(String[] args) {
        Arrays.stream(args)
                .peek(this::checkString)
                .map(s -> s.split("=", 2))
                .peek(strings -> strings[0] = strings[0].split("-", 2)[1])
                .forEach(s -> values.putIfAbsent(s[0], s[1]));
    }

    /*
     * Фабричный метод создает объект ArgsName и заполняет Map values на основе принятого массива аргументов
     * @param args переданный массив аргументов
     * @return возвращает объект типа ArgsName
     */
    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Incorrect number of argument");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
