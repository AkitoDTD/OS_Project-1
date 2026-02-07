public class SavingsAccount extends BankAccount {

    private double interestRate;

    public SavingsAccount(double balance) {
        super(balance);
        interestRate = 0.03;
    }

    public void addInterest() {
        double interest = balance * interestRate;
        updateBalance(interest);
    }
}
