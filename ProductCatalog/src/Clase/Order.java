package Clase;

import com.google.gson.*;

import java.util.*;
import java.io.*;

public class Order {
    private static int maxId = 0;
    private int id;
    private List<Product> products;
    private double totalPrice;
    private Date orderDate;

    static {

    }

    public Order(List<Product> products) {
        // Citirea fișierului JSON pentru a prelua ID-ul maxim
        try {
            FileReader reader = new FileReader("orders.json");
            JsonArray ordersArray = JsonParser.parseReader(reader).getAsJsonArray();
            for (JsonElement orderElement : ordersArray) {
                JsonObject orderObject = orderElement.getAsJsonObject();
                int orderId = orderObject.get("id").getAsInt();
                if (orderId > maxId) {
                    maxId = orderId;
                }
            }
        } catch (IOException e) {
            // Dacă fișierul nu există, maxId rămâne 0
            e.printStackTrace();
        }
        this.id = ++maxId;
        this.products = products;
        this.totalPrice = calculateTotalPrice();
        this.orderDate = new Date();
    }

    private double calculateTotalPrice() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Comanda ID: " + id + ", Produse: " + products + ", Total: " + totalPrice + ", Data: " + orderDate;
    }
}
