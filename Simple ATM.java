import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DynamicATM {
    private static double balance = 1000.00; // Initial balance
    private static final String PIN = "1234"; // Simple PIN 
    private static final double DAILY_WITHDRAWAL_LIMIT = 500.00;
    private static double dailyWithdrawnAmount = 0.00;
    private static List<String> transactionHistory = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your PIN: ");
        String inputPin = scanner.nextLine();
        if (!inputPin.equals(PIN)) {
            System.out.println("Incorrect PIN. Access denied.");
            return;
        }

        int choice;
        do {
            System.out.println("\n--- Welcome to Dynamic ATM ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. View Transaction History");
            System.out.println("5. Exit");
            System.out.print("Please select an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    depositMoney(scanner);
                    break;
                case 3:
                    withdrawMoney(scanner);
                    break;
                case 4:
                    viewTransactionHistory();
                    break;
                case 5:
                    System.out.println("Thank you for using Dynamic ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void checkBalance() {
        System.out.printf("Your current balance is: $%.2f%n", balance);
        transactionHistory.add("Checked Balance: $" + balance);
    }

    private static void depositMoney(Scanner scanner) {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            balance += amount;
            System.out.printf("Successfully deposited $%.2f. New balance: $%.2f%n", amount, balance);
            transactionHistory.add("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount. Please enter a positive number.");
        }
    }

    private static void withdrawMoney(Scanner scanner) {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount. Please enter a positive number.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance for this withdrawal.");
        } else if (dailyWithdrawnAmount + amount > DAILY_WITHDRAWAL_LIMIT) {
            System.out.println("Exceeded daily withdrawal limit. Limit remaining: $" + 
                               (DAILY_WITHDRAWAL_LIMIT - dailyWithdrawnAmount));
        } else {
            balance -= amount;
            dailyWithdrawnAmount += amount;
            System.out.printf("Successfully withdrew $%.2f. New balance: $%.2f%n", amount, balance);
            transactionHistory.add("Withdrew: $" + amount);
        }
    }

    private static void viewTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            System.out.println("--- Transaction History ---");
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }
}