public class BankAccount {

    protected double balance;

    public BankAccount() {
        balance = 0.0;
    }

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (validateTransaction(amount)) {
            updateBalance(amount);
        }
    }

    public void withdraw(double amount) {
        if (validateTransaction(amount)) {
            updateBalance(-amount);
        }
    }

    public double getBalance() {
        return balance;
    }

    public void transfer(double amount, BankAccount target) {
        if (validateTransaction(amount)) {
            this.withdraw(amount);
            target.deposit(amount);
        }
    }

    protected boolean validateTransaction(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return false;
        }
        if (balance + amount < 0) {
            System.out.println("Insufficient balance.");
            return false;
        }
        return true;
    }

    protected void updateBalance(double amount) {
        balance += amount;
    }
}
