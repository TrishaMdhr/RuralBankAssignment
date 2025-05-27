package ruralbank;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Rural Bank of Nepal");
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();

        Customer customer = new Customer(name);
        SavingsAccount savings = new SavingsAccount(name, "SAV123", 10000);
        CurrentAccount current = new CurrentAccount(name, "CUR123", 20000);
        customer.addAccount(savings);
        customer.addAccount(current);

        while (true) {
            System.out.println("\nChoose operation:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Add Interest");
            System.out.println("4. View Accounts");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            if (choice == 5) break;

            System.out.println("Select account (1: Savings, 2: Current): ");
            int accChoice = scanner.nextInt();
            BankAccount account = (accChoice == 1) ? savings : current;

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    double depAmount = scanner.nextDouble();
                    account.deposit(depAmount);
                    break;
                case 2:
                    System.out.print("Enter withdraw amount: ");
                    double withAmount = scanner.nextDouble();
                    try {
                        account.withdraw(withAmount);
                    } catch (InsufficientBalanceException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    if (account instanceof SavingsAccount) {
                        ((SavingsAccount) account).addInterest();
                    } else {
                        System.out.println("Interest only applies to savings accounts.");
                    }
                    break;
                case 4:
                    for (BankAccount acc : customer.getAccounts()) {
                        acc.displayAccountDetails();
                    }
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
        System.out.println("Thank you for using RBN!");
    }
}
