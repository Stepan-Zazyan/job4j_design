package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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
        Random random = new Random();
        List<String> answers = readPhrases();
        List<String> userInput = new ArrayList<>();
        String str = "";
        Scanner sc = new Scanner(System.in);
        while (!OUT.equals(str)) {
            str = sc.next();
            userInput.add(str);
            if (STOP.equals(str)) {
                userInput.add(STOP);
                while (!CONTINUE.equals(str)) {
                    str = sc.next();
                    userInput.add(str);
                    if (OUT.equals(str)) {
                        userInput.add(OUT);
                        break;
                    }
                }
            }
            if (!OUT.equals(str)) {
                String s = answers.get(random.nextInt(5));
                System.out.println(s);
                userInput.add(s);
            }
        }
        saveLog(userInput);
    }

    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(
                new FileReader("data/consoleInput.txt"))) {
            while (in.read() != -1) {
                list.add(in.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private void saveLog(List<String> log) {
        try (BufferedWriter out = new BufferedWriter(
                new FileWriter("data/consoleOutput.log",
                        StandardCharsets.UTF_8, true))) {
            for (String x : log) {
                out.write(x);
                out.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("data/consoleInput.txt", "data/consoleOutput.log");
        cc.run();
    }
}
