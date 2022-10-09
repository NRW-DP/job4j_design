package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            boolean startWrite = false;
            String line;
            while ((line = read.readLine()) != null) {
                String[] arr = line.split(" ", 2);
                boolean isAvailable = Integer.parseInt(arr[0]) < 400;
                if (!isAvailable == !startWrite) {
                    startWrite = !startWrite;
                    out.append(arr[1])
                            .append(';')
                            .append((startWrite ? "" : System.lineSeparator()));
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analyzes = new Analizy();
        analyzes.unavailable("./data/server.log", "./data/unavailable.csv");
    }
}

