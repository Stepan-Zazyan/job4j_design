package ru.job4j.ood.lsp.foodstorage.food;

import java.time.LocalDate;

public abstract class Food {
    private int id;
    private String name;
    private LocalDate expiryDate;
    private LocalDate createDate;
    private double price;
    private double discount;

    public Food() {

    }

    public Food(int id, String name, LocalDate expiryDate, LocalDate createDate, double price, double discount) {
        this.id = id;
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }


    public void consume() {
        System.out.println("Потреблять пищу");
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
