package ru.job4j.ood.lsp.foodstorage.food;

import java.time.LocalDate;
import java.util.Objects;

public class Milk extends Food {
    private int id;
    private String name;
    private LocalDate expiryDate;
    private LocalDate createDate;
    private double price;
    private double discount;

    public Milk(int id, String name, LocalDate expiryDate, LocalDate createDate, double price, double discount) {
        this.id = id;
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    @Override
    public void consume() {
        System.out.println("Пить молоко");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Milk{" + "id='"
                + id + "name='"
                + name + '\'' + ", expiryDate="
                + expiryDate + ", createDate="
                + createDate + ", price="
                + price + ", discount="
                + discount + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Milk milk = (Milk) o;
        return id == milk.id && Double.compare(price, milk.price) == 0
                && Double.compare(discount, milk.discount) == 0
                && Objects.equals(name, milk.name)
                && Objects.equals(expiryDate, milk.expiryDate)
                && Objects.equals(createDate, milk.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, expiryDate, createDate, price, discount);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
