package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Animal animal = new Animal(true, 100, "tiger",
                new Insects(), new String[] {"dog", "cat"});

        /* Преобразуем объект person в json-строку. */
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(animal));

        /* Создаём новую json-строку с модифицированными данными*/
        final String animalJson =
                "{"
                        + "\"predator\":true,"
                        + "\"speed\":100,"
                        + "\"name\":tiger,"
                        + "\"insect=\":"
                        + "{"
                        + "\"size\":\"0\""
                        + "},"
                        + "\"pride\":"
                        + "[\"dog\",\"cat\"]"
                        + "}";
        /* Превращаем json-строку обратно в объект */
        final Animal animalMod = gson.fromJson(animalJson, Animal.class);
        System.out.println(animalMod);
    }
}

class Animal {
   private boolean predator;
   private int speed;
   private String name;
   private Insects insect;
   private String[] pride;

    public Animal(boolean predator, int speed, String name, Insects insect, String[] pride) {
        this.predator = predator;
        this.speed = speed;
        this.name = name;
        this.insect = insect;
        this.pride = pride;
    }

    @Override
    public String toString() {
        return "Animal{"
                + "predator=" + predator
                + ", speed=" + speed
                + ", name='" + name + '\''
                + ", insect=" + insect
                + ", pride=" + Arrays.toString(pride)
                + '}';
    }
}

class Insects {
    private int size;
    private String name;
}
