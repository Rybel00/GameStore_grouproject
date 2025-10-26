package gamestore02;

import java.util.Scanner;

// 🔹 Parent class (Abstraction)
abstract class Product {
    private String name;
    private double price;
    private int stock;

    // Constructor
    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    // Getters & Setters (Encapsulation)
    public String getName() { 
        return name; 
    }
    public double getPrice() { 
        return price; 
    }
    public int getStock() { 
        return stock; 
    }
    public void setStock(int stock) { 
        this.stock = stock; 
        
    }
    // Display info (Polymorphism - can be overridden)
    public void displayInfo() {
        System.out.println(name + " - ₱" + price + " (" + stock + " left)");
    }
}

// 🔹 Child classes (Inheritance)
class PCGame extends Product {
    public PCGame(String name, double price, int stock) {
        super(name, price, stock);
    }

    @Override
    public void displayInfo() {
        System.out.print("[PC] ");
        super.displayInfo();
    }
}

class ConsoleGame extends Product {
    public ConsoleGame(String name, double price, int stock) {
        super(name, price, stock);
    }

    @Override
    public void displayInfo() {
        System.out.print("[Console] ");
        super.displayInfo();
    }
}

class MobileGame extends Product {
    public MobileGame(String name, double price, int stock) {
        super(name, price, stock);
    }

    @Override
    public void displayInfo() {
        System.out.print("[Mobile] ");
        super.displayInfo();
    }
}

// 🔹 Main Class
public class Gamestore02 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double total = 0;
        boolean nobuy = false;

        // Create array of games (Polymorphism in action)
        Product[] games = {
            new PCGame("Geometry Dash", 150, 5),
            new PCGame("Hollow Knight: Silk Song", 600, 0),
            new PCGame("Cyberpunk 2077", 2499.99, 2),
            new ConsoleGame("God of War", 2999.99, 3),
            new ConsoleGame("Spider-Man 2", 3499.99, 0),
            new ConsoleGame("Gran Turismo 7", 4955.50, 3),
            new MobileGame("Dead Cells", 349.99, 999),
            new MobileGame("Minecraft PE", 99.99, 0),
            new MobileGame("Muse Dash", 99.99, 5)
        };

        // Menu Loop
        while (true) {
            System.out.println("\n=== GAME STORE ===");
            System.out.println("[1] PC Games");
            System.out.println("[2] Console Games");
            System.out.println("[3] Mobile Games");
            System.out.println("[4] Finish Order");
            System.out.print("Choose: ");
            int choice = in.nextInt();

             if (choice == 4) {
                if (!nobuy) {
                    System.out.println("\nBut you didn't buy anything!");
                } else {
                    System.out.println("\nTotal Spent: ₱" + total);
                    System.out.println("Thanks for buying!");
                }
                break;
            }

            if (choice < 1 || choice > 3) {
                System.out.println("Invalid option!");
                continue;
            }

            // Show 3 games per category
            int start = (choice - 1) * 3;
            for (int i = 0; i < 3; i++) {
                System.out.print((i + 1) + ". ");
                games[start + i].displayInfo();
            }

            System.out.print("Pick a game: ");
            int pick = in.nextInt() - 1;
            Product game = games[start + pick];

            if (game.getStock() == 0) {
                System.out.println("Out of stock!");
                continue;
            }

            System.out.print("Quantity: ");
            int qty = in.nextInt();

            if (qty > game.getStock()) {
                System.out.println("Not enough stock!");
                continue;
            }

            // Update stock and total
            game.setStock(game.getStock() - qty);
            double cost = qty * game.getPrice();
            total += cost;

            System.out.println("Bought " + qty + " x " + game.getName() + " for ₱" + cost);
        }

        in.close();
    }
}