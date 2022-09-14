package ru.job4j.laboratory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class IOout {
    public static void main(String[] args) {
        String text = "Hello world";
        File file = new File("C:\\Users\\khlap\\Desktop\\ioOUT.txt");
        try (OutputStream os = new FileOutputStream(file)) {
            byte[] bytes = text.getBytes();
            os.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
