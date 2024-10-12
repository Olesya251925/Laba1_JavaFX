package org.example.laba1_javafx;

public class Dish {
    private String name;
    private double price;
    private int quantity;

    public Dish(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantity = 0;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return price * quantity; // Общая стоимость
    }

    @Override
    public String toString() {
        return name + " - " + price + " руб." + " (Количество: " + quantity + ")";
    }
}
