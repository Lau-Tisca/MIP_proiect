import Clase.*;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ProductCatalog catalog = new ProductCatalog();
        OrderHistory orderHistory = new OrderHistory();
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        try {
            catalog.loadCatalog("catalog.json");
            System.out.println("Catalog incarcat cu succes.");
            orderHistory.loadOrders(("orders.json"));
            System.out.println("Istoricul comenzilor incarcat cu succes.");
        } catch (IOException e) {
            System.out.println("Nu s-a putut incarca catalogul/istoricul. Se va crea unul nou.");
        }

        while (choice != 0) {
            System.out.println("\n     ~~Catalogul de produse~~");
            System.out.println("1. Adauga produs");
            System.out.println("2. Sterge produs");
            System.out.println("3. Afiseaza produsele");
            System.out.println("4. Afiseaza dupa categorie");
            System.out.println("5. Plaseaza o comanda");
            System.out.println("6. Afiseaza istoricul comenzilor");
            System.out.println("0. Iesi din aplicatie\n");
            System.out.print("Introdu alegerea: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consumare newline

            switch (choice) {
                case 1:
                    System.out.print("Introdu numele produsului: ");
                    String name = scanner.nextLine();
                    System.out.print("Introdu pretul produsului: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine(); // Consumare newline
                    System.out.print("Introdu categoria produsului: ");
                    String category = scanner.nextLine();
                    catalog.addProduct(new Product(name, price, category));
                    System.out.println("Produs adaugat cu succes.");
                    break;
                case 2:
                    System.out.print("Introdu numele produsului de sters: ");
                    String productName = scanner.nextLine();
                    boolean deleted = catalog.deleteProduct(productName);
                    if (deleted) {
                        System.out.println("Produs sters cu succes.");
                    }
                    else {
                        System.out.println("Produsul nu a fost gasit.");
                    }
                    //scanner.nextLine();
                    break;
                case 3:
                    catalog.displayProducts();
                    break;
                case 4:

                    System.out.print("Introdu categoria: ");
                    String filterCategory = scanner.nextLine();
                    catalog.filterByCategory(filterCategory);
                    break;
                case 5:
                    List<Product> selectedProducts = new ArrayList<>();
                    String selectedName = "";
                    System.out.println("Selecteaza produse pentru comanda. Introdu 'done' pentru a termina sau 'cancel' pentru a anula comanda.");
                    while (true) {
                        System.out.print("Introdu numele produsului: ");
                        selectedName = scanner.nextLine();
                        if (selectedName.equalsIgnoreCase("done") || selectedName.equalsIgnoreCase("cancel")) {
                            break;
                        }
                        var product = catalog.findProductByName(selectedName);
                        if (product.isPresent()) {
                            selectedProducts.add(product.get());
                            System.out.println("Produs adaugat la comanda.");
                        } else {
                            System.out.println("Produsul nu exista in catalog.");
                        }
                    }
                    if (selectedName.equalsIgnoreCase("done") && !selectedProducts.isEmpty()) {
                        Order order = new Order(selectedProducts);
                        orderHistory.addOrder(order);
                        System.out.println("Comanda plasata cu succes. ID comanda: " + order.getId());
                    } else {
                        System.out.println("Nu s-a plasat nicio comanda.");
                    }
                    break;
                case 6:
                    orderHistory.displayOrders();
                    break;
                case 0:
                    try {
                        catalog.saveCatalog("catalog.json");
                        System.out.println("Catalog salvat. La revedere!");
                        orderHistory.saveOrders("orders.json");
                        System.out.println("Istoricul comenzilor a fost salvat. La revedere!");
                    } catch (IOException e) {
                        System.out.println("Eroare la salvarea catalogului: " + e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Optiune invalida. Incearca din nou.");
            }
        }

        scanner.close();
    }
}