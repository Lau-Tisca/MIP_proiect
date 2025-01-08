package Clase;
import Interfete.CatalogOperations;

import java.io.*;
import java.util.*;
import com.google.gson.*;

// Clasa principală pentru gestionarea catalogului de produse
public class ProductCatalog implements CatalogOperations {

    // Lista de produse din catalog
    private List<Product> products;

    public ProductCatalog() {
        this.products = new ArrayList<>();
    }

    // Adăugare produs în catalog
    @Override
    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public boolean deleteProduct(String productName) {
        for (Product product : products) {
            if (product.getName().toUpperCase().equals(productName.toUpperCase())) {
                products.remove(product);
                return true;
            }
        }
        return false;
    }

    // Afișarea tuturor produselor din catalog
    @Override
    public void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("Catalogul este gol.");
        }
        else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    // Filtrare produse după categorie
    @Override
    public void filterByCategory(String category) {
        System.out.println("Produse din categoria " + category + ":");
        products.stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .forEach(System.out::println);
    }

    public Optional<Product> findProductByName(String selectedName) {
        return products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(selectedName))
                .findFirst();
    }

    @Override
    public void saveCatalog(String filename) throws IOException {
        try (Writer writer = new FileWriter(filename)) {
            Gson gson = new Gson();
            gson.toJson(products, writer);
        }
    }

    @Override
    public void loadCatalog(String filename) throws IOException {
        try (Reader reader = new FileReader(filename)) {
            Gson gson = new Gson();
            Product[] loadedProducts = gson.fromJson(reader, Product[].class);
            products = new ArrayList<>(Arrays.asList(loadedProducts));
        }
    }
}

