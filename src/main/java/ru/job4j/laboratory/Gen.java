package ru.job4j.laboratory;

public class Gen<T> {
    T ob;

    public Gen(T ob) {
        this.ob = ob;
    }

    public T getOb() {
        return ob;
    }
}

class Gen2 extends Gen<String> {

    public Gen2(String ob) {
        super(ob);
    }

    @Override
    public String getOb() {
        System.out.print("Вызван метод String getob(): ");
        return super.getOb();
    }
}

class BridgeDemo {
    public static void main(String[] args) {
        Gen2 strob2 = new Gen2("Тест сообщений");
        System.out.println(strob2.getOb());
    }
}