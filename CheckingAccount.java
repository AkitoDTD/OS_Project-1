public class CheckingAccount extends BankAccount {
	
	// Free trades (a constant shared by all accounts)
    private static final int FREE_TRANSACTIONS = 3;
    // Transaction fee for each trade beyond the limit
    private static final double TRANSACTION_FEE = 2.0;
    // Record the current number of trades
    private int transactionCount = 0;
    
    // Initialize balance and number of transactions
    public CheckingAccount(double balance) {
        super(balance);
    }
    
    // Execute parent class logic and count the number of transactions
    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        transactionCount++;
    }

    // Execute withdrawal after verifying balance and count the number of times
    @Override
    public void withdraw(double amount) {
        super.withdraw(amount);
        transactionCount++;
    }

    // Charges apply if the free limit is exceeded, and the counter will be reset
    public void deductFees() {
        if (transactionCount > FREE_TRANSACTIONS) {
            double fee = (transactionCount - FREE_TRANSACTIONS) * TRANSACTION_FEE;
            balance -= fee;
        }
        transactionCount = 0;
    }
}
