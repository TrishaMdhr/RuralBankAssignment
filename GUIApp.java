package ruralbank;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GUIApp extends Application {

    private Customer customer;
    private SavingsAccount savingsAccount;
    private CurrentAccount currentAccount;
    private TextArea outputArea = new TextArea();
    private ComboBox<String> accountSelector = new ComboBox<>();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Rural Bank of Nepal");

        TextField nameField = new TextField();
        Button createCustomerBtn = new Button("Create Customer");

        HBox nameBox = new HBox(10, new Label("Customer Name:"), nameField, createCustomerBtn);
        nameBox.setPadding(new Insets(10));

        accountSelector.getItems().addAll("Savings", "Current");
        accountSelector.setValue("Savings");

        Button depositBtn = new Button("Deposit");
        Button withdrawBtn = new Button("Withdraw");
        Button interestBtn = new Button("Add Interest");
        Button viewAccountsBtn = new Button("View Accounts");

        TextField amountField = new TextField();
        amountField.setPromptText("Enter amount");

        HBox transactionBox = new HBox(10, new Label("Account:"), accountSelector,
                new Label("Amount:"), amountField,
                depositBtn, withdrawBtn, interestBtn, viewAccountsBtn);
        transactionBox.setPadding(new Insets(10));

        outputArea.setEditable(false);
        outputArea.setPrefHeight(300);

        VBox root = new VBox(10, nameBox, transactionBox, outputArea);
        root.setPadding(new Insets(10));

        createCustomerBtn.setOnAction(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                customer = new Customer(name);
                savingsAccount = new SavingsAccount(name, "SAV123", 10000);
                currentAccount = new CurrentAccount(name, "CUR123", 20000);
                customer.addAccount(savingsAccount);
                customer.addAccount(currentAccount);
                output("Customer '" + name + "' created with Savings and Current accounts.");
            } else {
                output("Please enter a valid name.");
            }
        });

        depositBtn.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                BankAccount acc = getSelectedAccount();
                acc.deposit(amount);
                output("Deposited " + amount + " to " + accountSelector.getValue() + " account.");
            } catch (Exception ex) {
                output("Invalid input: " + ex.getMessage());
            }
        });

        withdrawBtn.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                BankAccount acc = getSelectedAccount();
                acc.withdraw(amount);
                output("Withdrew " + amount + " from " + accountSelector.getValue() + " account.");
            } catch (InsufficientBalanceException ex) {
                output("Error: " + ex.getMessage());
            } catch (Exception ex) {
                output("Invalid input: " + ex.getMessage());
            }
        });

        interestBtn.setOnAction(e -> {
            if (accountSelector.getValue().equals("Savings")) {
                savingsAccount.addInterest();
                output("Interest added to savings account.");
            } else {
                output("Interest applies only to savings accounts.");
            }
        });

        viewAccountsBtn.setOnAction(e -> {
            if (customer != null) {
                for (BankAccount acc : customer.getAccounts()) {
                    output(acc.getAccountHolderName() + " - " + acc.getAccountNumber() +
                            " - Balance: " + acc.getBalance());
                }
            } else {
                output("No customer found. Please create one.");
            }
        });

        primaryStage.setScene(new Scene(root, 800, 400));
        primaryStage.show();
    }

    private BankAccount getSelectedAccount() {
        return accountSelector.getValue().equals("Savings") ? savingsAccount : currentAccount;
    }

    private void output(String msg) {
        outputArea.appendText(msg + "\n");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
