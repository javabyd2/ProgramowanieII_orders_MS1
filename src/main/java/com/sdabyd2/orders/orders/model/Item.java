package com.sdabyd2.orders.orders.model;

import java.io.Serializable;

public class Item implements Serializable {
    private String productName;
    private int productCount;
    private double productPrice;

    public Item(String productName, int productCount, double productPrice) {
        this.productName = productName;
        this.productCount = productCount;
        this.productPrice = productPrice;
    }

    public Item() {
    }

    public double getValueOfItem() {
        return productCount * productPrice;
    }

    public double getValueOfItemIncludingRebate() {
        Double rebatedValue = getValueOfItem();
        if (this.productCount > 5 && this.productCount <= 10) {
            rebatedValue *= 1-.05;
        } else
            if (this.productCount > 10 && this.productCount <= 20) {
                rebatedValue *= 1 - .1;
            } else {
                if (this.productCount > 20) {
                    rebatedValue *= 1 - .15;
            }
        }
        return rebatedValue;
    }

    public String toString() {
        return padRight(productName, 20)
                +padLeft(String.format("%1.2f", productPrice), 10)+" zł"
                +padLeft(String.format("%d", productCount), 4)+" szt."
                +padLeft(String.format("%1.2f", getValueOfItem()), 10)+" zł";
    }

    private static String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s);
    }

    private static String padLeft(String s, int n) {
        return String.format("%1$" + n + "s", s);
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductCount() {
        return productCount;
    }
}
