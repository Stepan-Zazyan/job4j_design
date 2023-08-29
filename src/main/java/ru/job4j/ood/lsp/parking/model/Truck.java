package ru.job4j.ood.lsp.parking.model;

import java.util.Objects;

public class Truck extends Car {
    private int id;
    private int size;

    public Truck(int id, int size) {
        this.id = id;
        this.size = size;
    }

    public Truck() {
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Truck{" + "size="
                + size + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Truck truck = (Truck) o;
        return size == truck.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
