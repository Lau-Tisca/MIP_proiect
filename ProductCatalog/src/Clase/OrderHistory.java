package Clase;

import java.io.*;
import java.util.*;
import com.google.gson.*;

public class OrderHistory {
    private List<Order> orders;

    public OrderHistory() {
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void displayOrders() {
        if (orders.isEmpty()) {
            System.out.println("Nu exista comenzi in istoric.");
        } else {
            for (Order order : orders) {
                System.out.println(order);
            }
        }
    }

    public void saveOrders(String filename) throws IOException {
        try (Writer writer = new FileWriter(filename)) {
            Gson gson = new Gson();
            gson.toJson(orders, writer);
        }
    }

    public void loadOrders(String filename) throws IOException {
        try (Reader reader = new FileReader(filename)) {
            Gson gson = new Gson();
            Order[] loadedOrders = gson.fromJson(reader, Order[].class);
            orders = new ArrayList<>(Arrays.asList(loadedOrders));
        }
    }
}
