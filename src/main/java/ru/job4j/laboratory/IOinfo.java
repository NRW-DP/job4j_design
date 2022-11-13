package ru.job4j.laboratory;

import java.io.*;

public class IOinfo {
    public static void main(String[] args) throws IOException {
        /*
          Программа которая читает и записывает данные(байты) но с помощью буфера(массив где временно храниться)
         */
        try (InputStream is = new FileInputStream("even.txt");
        OutputStream os = new FileOutputStream("file1.txt")) {
            byte[] buffer = new byte[4096];
            int r = is.read(buffer);
            while (r != -1) {
                os.write(buffer, 0, r);
                r = is.read(buffer);
            }
        }
    }
}
