package com.sdabyd2.orders.orders.model;

public class Item {
    private String productName;
    private int productCount;
    private double productPrice;

    public Item(String productName, int productCount, double productPrice) {
        this.productName = productName;
        this.productCount = productCount;
        this.productPrice = productPrice;
    }

    public double getValueOfItem() {
        return productCount * productPrice;
    }

    public String toString() {
        return padRight(productName, 20)
                +padLeft(String.format("%1.2f", productPrice), 10)+" zł"
                +padLeft(String.format("%d", productCount), 4)+" szt."
                +padLeft(String.format("%1.2f", getValueOfItem()), 10)+" zł";
    }

    public static String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s);
    }

    public static String padLeft(String s, int n) {
        return String.format("%1$" + n + "s", s);
    }
}
