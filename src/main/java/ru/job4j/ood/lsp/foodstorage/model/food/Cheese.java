package ru.job4j.ood.lsp.foodstorage.model.food;

import java.time.LocalDate;
import java.util.Objects;

public class Cheese extends Food {
    private String name;
    private LocalDate expiryDate;
    private LocalDate createDate;
    private double price;
    private double discount;

    @Override
    public void consume() {
        System.out.println("Есть сыр");
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
        return "Cheese{" + "name='"
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
        Cheese cheese = (Cheese) o;
        return Double.compare(price, cheese.price) == 0
                && Double.compare(discount, cheese.discount) == 0 && Objects.equals(name, cheese.name)
                && Objects.equals(expiryDate, cheese.expiryDate) && Objects.equals(createDate, cheese.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, expiryDate, createDate, price, discount);
    }
}