package test;

import Clase.*;
import org.junit.Test;

import java.io.IOException;


public class CatalogOperationsTest {
    @Test
    public void testCatalogOperations() {
        ProductCatalog catalog = new ProductCatalog();

        // Test 1: Adaugare produs
        Product testProduct = new Product("Mouse", 50.0, "Electronice");
        catalog.addProduct(testProduct);
        System.out.println("Test 1 - Adaugare produs: ");
        catalog.displayProducts();

        // Test 2: Filtrare produse
        System.out.println("Test 2 - Filtrare dupa categorie 'Electronice':");
        catalog.filterByCategory("Electronice");

        // Test 3: Salvare si incarcare catalog
        try {
            String filename = "testCatalog.json";
            catalog.saveCatalog(filename);
            System.out.println("Test 3 - Salvare catalog: Succes");

            ProductCatalog loadedCatalog = new ProductCatalog();
            loadedCatalog.loadCatalog(filename);
            System.out.println("Test 3 - Incarcare catalog: Succes");
            loadedCatalog.displayProducts();
        } catch (IOException e) {
            System.out.println("Test 3 - Eroare: " + e.getMessage());
        }

        // Test 4: Afisare catalog gol
        ProductCatalog emptyCatalog = new ProductCatalog();
        System.out.println("Test 4 - Afisare catalog gol:");
        emptyCatalog.displayProducts();
    }
}