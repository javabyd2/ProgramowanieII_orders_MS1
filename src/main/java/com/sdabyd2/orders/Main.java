package com.sdabyd2.orders;

import com.sdabyd2.orders.orders.controller.Order;
import com.sdabyd2.orders.orders.model.Item;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception{
	    Item item1 = new Item("Chleb", 1, 3.5);
	    System.out.println(item1);

        Item item2 = new Item("Cukier", 3, 4);
        System.out.println(item2);

        Order order = new Order(20);
        order.addItem(item1);
        order.addItem(item2);
        System.out.println(order);

        File fileName = new File("obiekty.txt");
        fileName.createNewFile();

        OutputStream  fileOutputStream = null;
        ObjectOutput objectOutputStream = null;
        OutputStream bufferOut  = null;

        try {

            fileOutputStream = new FileOutputStream(fileName, true);
            bufferOut = new BufferedOutputStream(fileOutputStream);
            objectOutputStream = new ObjectOutputStream(bufferOut);

            objectOutputStream.writeObject(order);
            objectOutputStream.close();
        } finally {
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
            fileOutputStream.close();
            bufferOut.close();
            objectOutputStream.close();
        }
    }
}
