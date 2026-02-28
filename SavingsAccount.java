public class SavingsAccount extends BankAccount {
    private double interestRate;

    // Construct function to initialize balance and interest rate
    public SavingsAccount(double balance, double interestRate) {
        super(balance);
        this.interestRate = interestRate;
    }

   // Calculate and add interest to the account function
    public void addInterest() {

         // Check if balances are positive
        if (getBalance() > 0) {
            double interest = getBalance() * interestRate;
            updateBalance(interest);
            System.out.println("Interest added: $" + interest);

        // Show message if balance is not enough
        } else {
            System.out.println("Insufficient Balance To Get Interest");
        }
    }

}
