package ru.job4j.ood.lsp;

public class SpecialSmallClassroom extends Classroom {
    public SpecialSmallClassroom(float capacity) {
        super(capacity);
    }

    public void move(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Invalid distance!");
        }
        if (capacity > 15) { /*  постусловие ослаблено*/
            throw new IllegalArgumentException("Need more fuel!");
        }
        System.out.println("We study English");
    }
}