package ru.job4j.ood.lsp.parking.model;

public abstract class Car {
    private int id;
    private int size;

    public Car(int id, int size) {
        this.id = id;
        this.size = size;
    }

    public Car() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
