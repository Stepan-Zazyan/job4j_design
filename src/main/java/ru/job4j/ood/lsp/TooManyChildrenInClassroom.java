package ru.job4j.ood.lsp;

public class TooManyChildrenInClassroom extends Classroom {
    public TooManyChildrenInClassroom(float capacity) {
        super(capacity);
    }

    public void move(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Invalid distance!");
        }
        if (capacity > 60) { /*  условие усилено*/
            throw new IllegalArgumentException("Need more fuel!");
        }
        System.out.println("We study English");
    }
}
