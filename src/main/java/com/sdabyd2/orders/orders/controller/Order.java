package com.sdabyd2.orders.orders.controller;

import com.sdabyd2.orders.orders.model.Item;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Item> items = new ArrayList<>();
    private int maxItemsInOrder;

    public Order() {
        maxItemsInOrder = 10;
    }

    public Order(int maxItemsInOrder) {
        this.maxItemsInOrder = maxItemsInOrder;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public double getValueOfOrder() {
        Double value = 0.00;
        for (int i = 0; i < items.size(); i++) {
            value += items.get(i).getValueOfItem();
        }
        return value;
    }

    @Override
    public String toString() {
        String result = "\nZamówienie:";
        for (int i = 0; i < items.size(); i++) {
            result += "\n"+ items.get(i).toString();
        }
         return result +"\n\nRazem: "+ String.format("%1.2f", getValueOfOrder());
    }
}
