import java.util.Scanner;

public class ATMInterface {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Create a bank account with an initial balance
        BankAccount account = new BankAccount(1000.0); // Starting with $1000
        
        // Create an ATM machine with the user's bank account
        ATM atm = new ATM(account);
        
        boolean running = true;
        while (running) {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    atm.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: $");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: $");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.println("Exiting... Thank you for using our ATM.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        
        scanner.close();
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("Successfully deposited $%.2f\n", amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.printf("Successfully withdrew $%.2f\n", amount);
            return true;
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            System.out.println("Withdrawal amount must be positive.");
        }
        return false;
    }
}

class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void checkBalance() {
        System.out.printf("\nCurrent Balance: $%.2f\n", account.getBalance());
    }

    public void deposit(double amount) {
        account.deposit(amount);
        displayAccountSummary();
    }

    public void withdraw(double amount) {
        if (account.withdraw(amount)) {
            displayAccountSummary();
        }
    }

    private void displayAccountSummary() {
        System.out.printf("\n--- Account Summary ---\n");
        System.out.printf("%-20s: $%.2f\n", "Current Balance", account.getBalance());
    }
}
