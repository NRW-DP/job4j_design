package ru.job4j.laboratory;

import java.util.ArrayList;

public class ParameterizedMethodGenerics {
    public static void main(String[] args) {
        /*
          Происходит подстановка типа в метод GenMethod.getSecondElement
          Информацию о дженериках знает компилятор для проверок и jvm видит строку без дженериков,
          например - int a = i.get(0); -> jmv видит int a = (Integer)i.get(0);
          то есть каждый раз jmv кастит из Object в нужный тип,а потом назначается переменной - a , этот
          процесс называется стирание типов , это нужно для обратного совместимости кода, как так существовал код
          до JAVA5 , где небыло дженериков
          Нельзя создавать overload/override методы так как jvm не видит дженерики
          Стирание типов позволяет не создавать при применении дженериков новые классы
         */
        ArrayList<Integer> i = new ArrayList<>();
        i.add(1);
        i.add(2);
        i.add(3);
        int a = GenMethod.getSecondElement(i);
        System.out.println(a);

        ArrayList<String> s = new ArrayList<>();
        s.add("privet");
        s.add("poka");
        s.add("ok");
        String q = GenMethod.getSecondElement(s);
        System.out.println(q);
    }
}

/*
 * <T> - дженерик
 * Т - возвращаемый тип метода
 */
class GenMethod {
    public static <T> T getSecondElement(ArrayList<T> al) {
        return al.get(1);
    }
}

