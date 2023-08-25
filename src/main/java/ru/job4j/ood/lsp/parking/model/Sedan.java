package ru.job4j.ood.lsp.parking.model;

import java.util.Objects;

public class Sedan extends Car {
    private int id;
    private int size;

    public Sedan(int id) {
        this.id = id;
        this.size = 1;
    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Sedan{" + "size="
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
        Sedan sedan = (Sedan) o;
        return size == sedan.size;
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
