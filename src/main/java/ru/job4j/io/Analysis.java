package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        int counter = 0;
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            try (BufferedWriter out = new BufferedWriter(new FileWriter(target))) {
                for (String line = in.readLine(); line != null; line = in.readLine()) {
                    String[] res = line.split(" ");
                    if (counter > 0 && Integer.parseInt(res[0]) >= 400) {
                        continue;
                    }
                    if (Integer.parseInt(res[0]) >= 400) {
                        out.append(res[1]).append("; ");
                        counter += 1;
                    }
                    if (counter > 0 && Integer.parseInt(res[0]) < 400) {
                        out.append(res[1]).append(";").append(System.lineSeparator());
                        counter = 0;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
