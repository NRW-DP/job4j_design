package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter write = new PrintWriter(new FileOutputStream(target))) {
            String line;
            boolean marker = false;
            while ((line = read.readLine()) != null) {
                if (line.contains("400") || line.contains("500")) {
                    if (!marker) {
                        String[] in = line.split(" ");
                        write.print(in[1] + ";");
                        marker = true;
                    }
                } else if (marker) {
                    String[] in = line.split(" ");
                    write.println(in[1] + ";");
                    marker = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analyzes = new Analizy();
        analyzes.unavailable("./data/server.log", "./data/unavailable.csv");
    }
}

