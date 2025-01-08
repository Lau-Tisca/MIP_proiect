package Interfete;
import Clase.*;

import java.io.IOException;

public interface CatalogOperations {
    void addProduct(Product product);
    boolean deleteProduct(String productName);
    void displayProducts();
    void filterByCategory(String category);
    void saveCatalog(String filename) throws IOException;
    void loadCatalog(String filename) throws IOException;
}
