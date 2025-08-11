import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Account {
    private String userId;
    private int pin;
    private double balance;
    private String[] transactionHistory;

    public Account(String userId, int pin, double balance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = balance;
        this.transactionHistory = new String[10];
    }

    public String getUserId() {
        return userId;
    }

    public int getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String[] getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(String transaction, int index) {
        this.transactionHistory[index] = transaction;
    }
}

public class ATM {
    private Map<String, Account> accounts;
    private Scanner scanner;

    public ATM() {
        accounts = new HashMap<>();
        scanner = new Scanner(System.in);

        // Initialize accounts
        accounts.put("user1", new Account("user1", 1234, 1000));
        accounts.put("user2", new System.out.println("Welcome to ATM");
        System.out.print("Enter user ID: ");
        String userId = scanner.next();
        System.out.print("Enter PIN: ");
        int pin = scanner.nextInt();

        if (accounts.containsKey(userId) && accounts.get(userId).getPin() == pin) {
            System.out.println("Login successful!");
            accountMenu(accounts.get(userId));
        } else {
            System.out.println("Invalid user ID or PIN.");
        }
    }

    public void accountMenu(Account account) {
        while (true) {
            System.out.println("1. Transactions History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    transactionsHistory(account);
                    break;
                case 2:
                    withdraw(account);
                    break;
                case 3:
                    deposit(account);
                    break;
                case 4:
                    transfer(account);
                    break;
                case 5:
                    System.out.println("Thank you for using ATM.");
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    public void transactionsHistory(Account account) {
        System.out.println("Transactions History:");
        for (String transaction : account.getTransactionHistory()) {
            if (transaction != null) {
                System.out.println(transaction);
            }
        }
    }

    public void withdraw(Account account) {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();

        if (account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            account.setTransactionHistory("Withdrawal: " + amount, 0);
            System.out.println("Withdrawal successful!");
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void deposit(Account account) {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();

        account.setBalance(account.getBalance() + amount);
        account.setTransactionHistory("Deposit: " + amount, 0);
        System.out.println("Deposit successful!");
    }

    public void transfer(Account account) {
        System.out.print("Enter user ID to transfer: ");
        String recipientUserId = scanner.next();
        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();

        if (accounts.containsKey(recipientUserId)) {
            Account recipientAccount = accounts.get(recipientUserId);
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
                recipientAccount.setBalance(recipientAccount.getBalance() + amount);
                account.setTransactionHistory("Transfer to " + recipientUserId + ": " + amount, 0);
                System.out.println("Transfer successful!");
            } else {
                System.out.println("Insufficient balance.");
            }
        } else {
            System.out.println("Recipient user ID not found.");
        }
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
    }
}
