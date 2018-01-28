package com.sdabyd2.orders.orders.controller;

import com.sdabyd2.orders.orders.model.Item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Order implements Serializable {

    private List<Item> items = new ArrayList<>();
    private int maxItemsInOrder;

    public Order() {
        maxItemsInOrder = 10;
    }

    public Order(int maxItemsInOrder) {
        this.maxItemsInOrder = maxItemsInOrder;
    }

    public void addItem(Item item) {
        int index = -1;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(0).getProductName().equals(item.getProductName())) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            items.add(item);
        } else {
            items.get(index).setProductCount(items.get(index).getProductCount()+item.getProductCount());
        }
    }

    public double getValueOfOrder() {
        Double value = 0.00;
        Double rebateValue = 0.00;
        for (int i = 0; i < items.size(); i++) {
            value += items.get(i).getValueOfItem();
            rebateValue += value - items.get(i).getValueOfItemIncludingRebate();
        }
        return value;
    }

    @Override
    public String toString() {
        Double value = 0.00;
        Double rebatedValue = 0.00;
        String result = "\nZamówienie:";
        for (int i = 0; i < items.size(); i++) {
            result += "\n" + items.get(i).toString();
            rebatedValue += items.get(i).getValueOfItem() - items.get(i).getValueOfItemIncludingRebate();
        }
        value = getValueOfOrder();
        return result + "\n\nRazem: " + String.format("%1.2f", value)
                +"\nRabat: " + String.format("%1.2f", rebatedValue)
                +"\nRazem po rabacie: " + String.format("%1.2f", rebatedValue);
    }

    public void eraseItem(int index) {
        items.remove(index);
    }

    public void editItem(int index) {
        Item item = new Item();
        item = items.get(index);

        Scanner input = new Scanner(System.in);
        System.out.println("Edycja pozycji:\n"+items.get(index).toString());
        System.out.print("Proszę podać nazwę produktu: ");
        item.setProductName(input.next());
        System.out.print("Proszę podać ilość: ");
        item.setProductCount(input.nextInt());
        System.out.print("Proszę podać cenę jednostkową: ");
        item.setProductPrice(input.nextDouble());
    }
}
