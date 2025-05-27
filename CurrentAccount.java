package ruralbank;

public class CurrentAccount extends BankAccount {
    private static final double OVERDRAFT_LIMIT = 5000;

    public CurrentAccount(String name, String accountNumber, double balance) {
        super(name, accountNumber, balance);
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > balance + OVERDRAFT_LIMIT) {
            throw new InsufficientBalanceException("Withdrawal failed: Exceeds overdraft limit!");
        }
        balance -= amount;
        System.out.println("Withdrawn: " + amount);
    }

    public void displayAccountDetails() {
        System.out.println("Current Account - Holder: " + getAccountHolderName() +
                ", Acc No: " + getAccountNumber() +
                ", Balance: " + balance);
    }
}
