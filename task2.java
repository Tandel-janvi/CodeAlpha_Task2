import java.util.*;

// Class to represent a Stock
class Stock {
    String name;
    double price;

    Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

// Class to represent a User Portfolio
class User {
    String username;
    double balance;
    Map<String, Integer> portfolio = new HashMap<>();

    User(String username, double balance) {
        this.username = username;
        this.balance = balance;
    }

    // Buy Stock
    void buyStock(Stock stock, int quantity) {
        double totalCost = stock.price * quantity;
        if (balance >= totalCost) {
            balance -= totalCost;
            portfolio.put(stock.name, portfolio.getOrDefault(stock.name, 0) + quantity);
            System.out.println("Bought " + quantity + " shares of " + stock.name);
        } else {
            System.out.println(" Not enough balance to buy " + stock.name);
        }
    }

    // Sell Stock
    void sellStock(Stock stock, int quantity) {
        if (portfolio.getOrDefault(stock.name, 0) >= quantity) {
            portfolio.put(stock.name, portfolio.get(stock.name) - quantity);
            balance += stock.price * quantity;
            System.out.println(" Sold " + quantity + " shares of " + stock.name);
        } else {
            System.out.println(" You don’t have enough shares to sell!");
        }
    }

    // Display portfolio
    void showPortfolio(Map<String, Stock> market) {
        System.out.println("\n Portfolio of " + username + ":");
        double totalValue = balance;
        for (String stockName : portfolio.keySet()) {
            int qty = portfolio.get(stockName);
            double stockValue = market.get(stockName).price * qty;
            totalValue += stockValue;
            System.out.println(stockName + ": " + qty + " shares (Rs." + stockValue + ")");
        }
        System.out.println(" Balance: Rs." + balance);
        System.out.println(" Total Portfolio Value: Rs." + totalValue + "\n");
    }
}

// Main Class
public class task2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Market data (You can update these prices)
        Map<String, Stock> market = new HashMap<>();
        market.put("TCS", new Stock("TCS", 3500));
        market.put("INFY", new Stock("INFY", 1600));
        market.put("RELIANCE", new Stock("RELIANCE", 2400));

        // Create user
        User user = new User("Raj", 30000);

        int choice;
        do {
            System.out.println("\n=== STOCK TRADING PLATFORM ===");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n Market Stocks:");
                    for (Stock s : market.values()) {
                        System.out.println(s.name + " Rs. " + s.price);
                    }
                    break;

                case 2:
                    System.out.print("Enter stock name to buy: ");
                    String buyName = sc.next().toUpperCase();
                    if (market.containsKey(buyName)) {
                        System.out.print("Enter quantity: ");
                        int qty = sc.nextInt();
                        user.buyStock(market.get(buyName), qty);
                    } else {
                        System.out.println(" Stock not found!");
                    }
                    break;

                case 3:
                    System.out.print("Enter stock name to sell: ");
                    String sellName = sc.next().toUpperCase();
                    if (market.containsKey(sellName)) {
                        System.out.print("Enter quantity: ");
                        int qty = sc.nextInt();
                        user.sellStock(market.get(sellName), qty);
                    } else {
                        System.out.println(" Stock not found!");
                    }
                    break;

                case 4:
                    user.showPortfolio(market);
                    break;

                case 5:
                    System.out.println(" Thank you for trading!");
                    break;

                default:
                    System.out.println(" Invalid choice!");
            }
        } while (choice != 5);

        sc.close();
    }
}
