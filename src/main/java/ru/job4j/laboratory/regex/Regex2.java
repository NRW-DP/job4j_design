package ru.job4j.laboratory.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex2 {
    public static void main(String[] args) {
        String s1 = " ABC ABC4 ADBABCCG DMSLABCFLGN";
        /*
       Pattern pattern1 = Pattern.compile("ABC");
       Matcher matcher = pattern1.matcher(s1);
        */
        Pattern pattern1 = Pattern.compile("[ABC]");
        Matcher matcher = pattern1.matcher(s1);
        /*
        Создаем шаблон с помощью класса Паттерн. наш шаблон содержит регулярное выражение - ABC,
        потом с помощью класса Matcher ищем в строке наш паттерн - pattern1 и результат передаем объекту matcher
        затем с помощью .find() есть ли совпрадения и если они есть выводим на экран с помощью метода .group()

        Квадратные скобки - ("[ABC]") -дает выбор искомого элемента , то есть лобо А либо B либо С
         */

        while (matcher.find()) {
            /*
            .find возвращает true/false при нахождении элемента , после того как совпадения закончаться
            метод вернет false
            в переменной s1 найдет 4 соответствия - ABC
            .start() - показывает на какой позиции находится соответсвие с паттерном
             */
            System.out.println("Positions " + matcher.start() + "   " + matcher.group());
        }

    }
}
