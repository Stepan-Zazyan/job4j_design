package ru.job4j.ood.lsp;

public class NoEnglishClassroom extends Classroom {
    public NoEnglishClassroom(float capacity) {
        super(capacity);
    }

    public void move(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Invalid distance!");
        }
        if (capacity > 30) {
            throw new IllegalArgumentException("Need more fuel!");
        }
        /*  не все условия базового класса сохранены в подклассе*/
    }
}