package ruralbank;

public class SavingsAccount extends BankAccount {
    private static final double INTEREST_RATE = 0.05; // 5%

    public SavingsAccount(String name, String accountNumber, double balance) {
        super(name, accountNumber, balance);
    }

    public void addInterest() {
        double interest = balance * INTEREST_RATE;
        balance += interest;
        System.out.println("Interest added: " + interest);
    }


    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException("Withdrawal failed: Insufficient balance!");
        }
        balance -= amount;
        System.out.println("Withdrawn: " + amount);
    }

    public void displayAccountDetails() {
        System.out.println("Savings Account - Holder: " + getAccountHolderName() +
                ", Acc No: " + getAccountNumber() +
                ", Balance: " + balance);
    }
}
