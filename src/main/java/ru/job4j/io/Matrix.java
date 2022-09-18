package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class Matrix {

    public static void multiple(int size) {
        try (FileOutputStream out = new FileOutputStream("matrix.txt")) {
            for (int i = 1; i <= size; i++) {
                for (int k = 1; k <= size; k++) {
                    String rsl = i * k + " ";
                    out.write(rsl.getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        multiple(5);
    }
}
