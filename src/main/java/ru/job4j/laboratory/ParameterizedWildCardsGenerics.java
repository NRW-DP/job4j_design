package ru.job4j.laboratory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ParameterizedWildCardsGenerics {
    public static void main(String[] args) {
        /*
         * Ковариантность — это сохранение иерархии наследования исходных типов в производных типах в том же порядке
         * Множество<Животные>  = Множество<Кошки>
         * Контравариантность — это обращение иерархии исходных типов на противоположную в производных типах
           Множество<Кошки> = Множество<Животные>
         * Инвариантность — отсутствие наследования между производными типами.
           Множество<Кошки> != Множество<Животные>
         */
        String[] strings = new String[] {"a", "b", "c"};
        Object[] arr = strings;

        /*
        Это ковариантность. List<Integer> — подтип List<? extends Number>
         */
        List<Integer> ints = new ArrayList<Integer>();
        List<? extends Number> nums = ints;


        /*
        Это контравариантность. List<Number> является подтипом List<? super Integer>.
         List<Number> nums1 = new ArrayList<Number>();
         List<? super Integer> int = nums;
         */

        /*
        Запись вида "? extends ..." или "? super ..." — называется wildcard или символом подстановки,
        с верхней границей (extends) или с нижней границей (super).
        ? extends - ограничение сверху - читает (добавляет только null)
        ? super - ограничение снизу - добавляет (прочитает только Object)
        In-аргумент - предоставляет значение - то есть это get значение
        Out - аргумент - получает значение

        List<? extends Number> может содержать объекты, класс которых является Number или наследуется от Number.
        List<? super Number> может содержать объекты, класс которых Number
        или  у которых Number является наследником (супертип от Number).
        Особенность wildcard с верхней и нижней границей дает дополнительные фичи, связанные с безопасным использованием типов.
        Из одного типа переменных можно только читать, в другой — только вписывать
        (исключением является возможность записать null для extends и прочитать Object для super).
        Чтобы было легче запомнить, когда какой wildcard использовать,
        существует принцип PECS — Producer Extends Consumer Super.

        Если мы объявили wildcard с extends, то это producer.
        Он только «продюсирует», предоставляет элемент из контейнера, а сам ничего не принимает.
        Если же мы объявили wildcard с super — то это consumer.
        Он только принимает, а предоставить ничего не может.
         */

    }
}
