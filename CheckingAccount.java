public class CheckingAccount extends BankAccount {

    private static final int FREE_TRANSACTIONS = 3;
    private static final double TRANSACTION_FEE = 2.0;

    private int transactionCount = 0;

    public CheckingAccount(double balance) {
        super(balance);
    }

    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        transactionCount++;
    }

    @Override
    public void withdraw(double amount) {
        super.withdraw(amount);
        transactionCount++;
    }

    public void deductFees() {
        if (transactionCount > FREE_TRANSACTIONS) {
            double fee = (transactionCount - FREE_TRANSACTIONS) * TRANSACTION_FEE;
            balance -= fee;
        }
        transactionCount = 0;
    }
}
