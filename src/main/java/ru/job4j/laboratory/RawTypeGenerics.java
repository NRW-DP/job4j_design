package ru.job4j.laboratory;

import java.util.ArrayList;
import java.util.List;

public class RawTypeGenerics {
    /**
        В этом классе описывается сырой тип - Raw Type, так как лист непараметризированный то он содержит Object
        (потому в цикле for перебераем тип Object)
        и для того что бы получить длинну слов нужно кастить list к String
        Использования raw type это плохая практика так как в лист можно добавить разные значения и не только String
        list.add(8);
        list.add(new Car());
        при добавлении ошибки не будет так как это не параметризированный тип ,в лист войдет всё,
        но мы обнаружим ошибку при кастинге ClassCastException
        потому что бы избежать таких проблем используем <ПАРАМЕТР>
        List <ПАРАМЕТР>list = new ArrayList<ПАРАМЕТР>();
        <> - скобки называются diamond symbol
        Основные причины существования generics это
        1. Type Safe - создание коллекций определенных типов и содержат определенные элементы и на уровне компиляции
        позволяют находить ошибки
        2. Reusable Code - позволяет не создавать похожие классы/методы/код для каждого типа коллекции
     */
    public static void main(String[] args) {
        List list = new ArrayList();

        /*
         указываем тип List<String> list = new ArrayList<>();
         */
        list.add("privet");
        list.add("poka");
        list.add("ok");
        list.add("ne ok");

        for (Object o : list) {
            System.out.println(o + " - > dlina " + ((String) o).length());
        }

    }
}
