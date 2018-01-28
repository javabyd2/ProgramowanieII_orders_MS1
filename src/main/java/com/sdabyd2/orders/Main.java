package com.sdabyd2.orders;

import com.sdabyd2.orders.orders.controller.Order;
import com.sdabyd2.orders.orders.model.Item;

public class Main {

    public static void main(String[] args) {
	    Item item1 = new Item("Chleb", 1, 3.5);
	    System.out.println(item1);

        Item item2 = new Item("Cukier", 3, 4);
        System.out.println(item2);

        Order order = new Order(20);
        order.addItem(item1);
        order.addItem(item2);
        System.out.println(order);
    }
}
