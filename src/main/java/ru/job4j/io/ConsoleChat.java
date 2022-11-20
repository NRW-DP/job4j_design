package ru.job4j.io;

import java.io.*;
import java.nio.Buffer;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {

    }

    /**
     * метод читает из строки переписку и соберает ёё в лист
     * @return - лист собранной переписки
     */
    private List<String> readPhrases() {
        List<String> answersList = null;
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers))) {
            answersList = in.lines().map(s -> s + System.lineSeparator()).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answersList;
    }

    /**
     * @param log - лист в который сохраняется вся переписка чата
     */
    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(
                new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            log.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("", "");
        cc.run();
    }
}
