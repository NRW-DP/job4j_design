package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            in.lines()
                    .filter(line -> line.contains(" 404 "))
                    .forEach(list::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


    public static void save(List<String> log, String file) {
        try (PrintWriter outWrite = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
            log.forEach(outWrite::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        for (String i : log) {
            System.out.println(i);
        }
        save(log, "404.txt");
    }
}
