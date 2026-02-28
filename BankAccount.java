public class BankAccount {
    protected double balance;

    public BankAccount() {
        this.balance = 0.0;
    }

    public BankAccount(double balance) {
        this.balance = balance;
    }

    // Adds money to the balance after checking that the amount is positive
    public void deposit(double amount) {
        if (validateTransaction(amount)) {
            updateBalance(amount);
        }
    }

    // Subtracts money after checking amount and checking for sufficient funds
    public void withdraw(double amount) {
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

    // Moves money from this account object to another account object in the Heap
    public void transfer(double amount, BankAccount target) {
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

    // The only method that directly modifies the protected balance
    protected void updateBalance(double amount) {
        balance += amount;
    }
}