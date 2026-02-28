public class BankAccount {
    protected double balance;

    public BankAccount() {
        this.balance = 0.0;
    }

    public BankAccount(double balance) {
        this.balance = balance;
    }

    // Synchronized, prevents Race Conditions when multiple threads deposit concurrently
    public synchronized void deposit(double amount) {
        if (validateTransaction(amount)) {
            updateBalance(amount);
        }
    }

    // Synchronized, ensures thread-safe withdrawals
    public synchronized void withdraw(double amount) {
        if (validateTransaction(amount)) {
            if (balance - amount < 0) {
                System.out.println("Insufficient balance.");
            } else {
                updateBalance(-amount);
            }
        }
    }

    public double getBalance() {
        return balance;
    }

    // Locks the method so transfers process atomically without thread interference
    public synchronized void transfer(double amount, BankAccount target) {
        if (validateTransaction(amount)) {
            if (this.balance >= amount) {
                this.withdraw(amount); // Remove from source
                target.deposit(amount); // Add to destination
            } else {
                System.out.println("Transfer failed: Insufficient balance.");
            }
        }
    }

    // Helper method to prevent negative input values
    protected boolean validateTransaction(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return false;
        }
        return true;
    }

    // The only method that directly modifies the protected balance. 
    // Synchronized to lock the critical section of memory.
    protected synchronized void updateBalance(double amount) {
        balance += amount;
    }
}