package com.sdabyd2.orders.orders.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdabyd2.orders.orders.model.Item;

import java.io.*;
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

    public double countValueOfOrder() {
        Double value = 0.00;
        for (int i = 0; i < items.size(); i++) {
            value += items.get(i).countValueOfItem();
        }
        return value;
    }
    
    @Override
    public String toString() {
        Double value = 0.00;
        Double rebatValue = 0.00;
        String result = "\nZamówienie:";
        for (int i = 0; i < items.size(); i++) {
            result += "\n" + items.get(i).toString();
            value += items.get(i).countValueOfItem();
            rebatValue += items.get(i).countValueOfItem() - items.get(i).countValueOfItemIncludingRebate();
        }
        return result + "\n\nRazem: " + String.format("%1.2f", value)
                +"\nRabat: " + String.format("%1.2f", rebatValue)
                +"\nRazem po rabacie: " + String.format("%1.2f", value - rebatValue);
    }

    public void deleteItem(int index) {
        items.remove(index);
    }

    public void editItem(int index) {
        Item item = items.get(index);

        Scanner input = new Scanner(System.in);
        System.out.println("Edycja pozycji:\n"+items.get(index).toString());
        System.out.print("Proszę podać nazwę produktu: ");
        item.setProductName(input.next());
        System.out.print("Proszę podać ilość: ");
        item.setProductCount(input.nextInt());
        System.out.print("Proszę podać cenę jednostkową: ");
        item.setProductPrice(input.nextDouble());
    }

    public static void saveOrderToFile(Order order, String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File filename = new File(fileName+".json");
        filename.createNewFile();
        mapper.writeValue(filename, order);
    }

    public static Order loadOrderFromFile(String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        Order order = new Order();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName+".json"));
            String json;
            while ((json = bufferedReader.readLine()) != null) {
                order = objectMapper.readValue(json, Order.class);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Wystąpił problem z odczytem pliku zamówienia. "+e.getMessage());
        }
        return order;
    }

    public List<Item> getItems() {
        return items;
    }

    public int getMaxItemsInOrder() {
        return maxItemsInOrder;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setMaxItemsInOrder(int maxItemsInOrder) {
        this.maxItemsInOrder = maxItemsInOrder;
    }
}
