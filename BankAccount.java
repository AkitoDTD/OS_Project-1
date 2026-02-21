public class BankAccount {

    protected double balance;

    public BankAccount() {
        this.balance = 0.0;
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
            if (balance - amount < 0) {
                System.out.println("Insufficient balance.");
            }
            else {
                updateBalance(-amount);
            }
        }
    }

    public double getBalance() {
        return balance;
    }

    public void transfer(double amount, BankAccount target) {
        if (validateTransaction(amount)) {
            if (this.balance >= amount) {
                this.withdraw(amount);
                target.deposit(amount);
            } 
            else {
                System.out.println("Insufficient balance.");
            }
        }
    }

    protected boolean validateTransaction(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return false;
        }
        return true;
    }

    protected void updateBalance(double amount) {
        balance += amount;
    }
}