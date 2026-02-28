public class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(double balance, double interestRate) {
        super(balance);
        this.interestRate = interestRate;
    }

    // Calculates interest growth. This is a background financial process.
    public void addInterest() {
        if (getBalance() > 0) {
            double interest = getBalance() * interestRate;
            updateBalance(interest);
            System.out.println("Interest added: $" + interest);
        } else {
            System.out.println("Insufficient Balance To Get Interest");
        }
    }
}