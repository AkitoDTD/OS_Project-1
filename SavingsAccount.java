public class SavingsAccount extends BankAccount {

    private double interestRate;

    public SavingsAccount(double balance, double interestRate) {
        super(balance);
        this.interestRate = interestRate;
    }

    public void addInterest() {
        if(getBalance() > 0){
        double interest = getBalance() * interestRate;
        updateBalance(interest);
        }
        else{
             System.out.println("Insufficient Balance To Get Interest");
        }
    }
}


