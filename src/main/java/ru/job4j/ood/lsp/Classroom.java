package ru.job4j.ood.lsp;

public class Classroom {
    protected int capacity;

    public Classroom(float capacity) {
        this.capacity = (int) capacity;
    }

    public void makeLesson(int children) {
        if (children < 0) {
            throw new IllegalArgumentException("Invalid distance!");
        }
        if (children > 30) { /* условие */
            throw new IllegalArgumentException("Need more fuel!");
        }
        System.out.println("We study English");
    }
}
