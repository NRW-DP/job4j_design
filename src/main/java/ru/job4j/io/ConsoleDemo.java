package ru.job4j.io;

import java.io.Console;
import java.util.Arrays;

public class ConsoleDemo {

    public static void main(String[] args) {
        String login;
        char[] charPassword;
        Console console = System.console();
        if (console == null) {
            System.out.println("The console is not available");
            return;
        }
        login = console.readLine("%s", "Enter your login: ");
        console.printf("Your login: %s\n", login);
        charPassword = console.readPassword("%s", "Enter your password: ");
        System.out.println("Your password: " + String.valueOf(charPassword));
        Arrays.fill(charPassword, ' ');
    }

}