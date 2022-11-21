package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private static final String HELP = "помощь";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        System.out.println("Задавайте ваши вопросы.");
        System.out.println("Для справки по командам введите \"помощь\"");
        saveLog(dialog());
    }

    public void printHelpMessage() {
        System.out.println("Список команд:");
        System.out.println("помощь - вывод данного списка команд");
        System.out.println("стоп - остановить ответы на вопросы");
        System.out.println("продолжить - возобновить ответы на вопросы");
        System.out.println("закончить - завершить программу");
    }

    private List<String> dialog() {
        List<String> log = new ArrayList<>();
        List<String> answer = readPhrases();
        boolean doAnswers = true;
        Random random = new Random();
        Scanner sc = new Scanner(System.in);
        String question;
        do {
            System.out.println("Вопрос: ");
            question = sc.nextLine();
            log.add("Вопрос:" + question);
            switch (question) {
                case HELP:
                    printHelpMessage();
                    break;

                case STOP:
                    doAnswers = false;
                    System.out.println("Ответы остановнелы...");
                    break;

                case CONTINUE:
                    doAnswers = true;
                    System.out.println("Возобновить ответы...");
                    break;

                case OUT:
                    System.out.println("Удачи :-) ");
                    break;

                default:
                    if (doAnswers) {
                        String tempAnswer = answer.get(random.nextInt(answer.size()));
                        log.add("Ответ: " + tempAnswer);
                        System.out.println(tempAnswer);
                    }
                    break;
            }
        } while (!OUT.equals(question));
        return log;
    }

    /**
     * метод читает из строки переписку и соберает ёё в лист
     *
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
        ConsoleChat cc = new ConsoleChat(
                "data/botAnswers.txt",
                "data/botLog.txt");
        cc.run();
    }
}
