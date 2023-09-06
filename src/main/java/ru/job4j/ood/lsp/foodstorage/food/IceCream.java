package ru.job4j.ood.lsp.foodstorage.food;

import java.time.LocalDate;
import java.util.Objects;

public class IceCream extends Food {
    private int id;
    private String name;
    private LocalDate expiryDate;
    private LocalDate createDate;
    private double price;
    private double discount;

    public IceCream(int id, String name, LocalDate expiryDate, LocalDate createDate, double price, double discount) {
        this.id = id;
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    @Override
    public void consume() {
        System.out.println("Есть мороженое");
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
        return "IceCream{" + "id='"
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
        IceCream iceCream = (IceCream) o;
        return id == iceCream.id && Double.compare(price, iceCream.price) == 0
                && Double.compare(discount, iceCream.discount) == 0
                && Objects.equals(name, iceCream.name)
                && Objects.equals(expiryDate, iceCream.expiryDate)
                && Objects.equals(createDate, iceCream.createDate);
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