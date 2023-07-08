package ru.job4j.ood.tdd;

import java.util.Objects;

public class Ticket3D implements Ticket {
    int row;
    int column;

    public Ticket3D() {
    }

    public Ticket3D(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ticket3D ticket3D = (Ticket3D) o;
        return row == ticket3D.row && column == ticket3D.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
