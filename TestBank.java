public class TestBank {

    public static void main(String[] args) {
        //Two Bankaccount were created
        BankAccount acc1 = new BankAccount(1000);
        BankAccount acc2 = new BankAccount(500);

        acc1.deposit(200); //Acc1 deposit 200 into its bankaccount, current balance = 1200
        acc1.withdraw(100);//Acc1 withdraw 100 into its bankaccount, current balance = 1100
        acc1.transfer(300, acc2); 
        // Acc1 transfer 300 into Acc2, Acc1's current balance = 800, Acc2's current balance = 800
 
        // Callstack is used on acc1 which deposit 100 into Acc1, current Acc1's balance = 900
        CallStack(acc1); 
        

        primitiveVsObject(50, acc1);
        StringImmutability();
        
        try {
            // it call the function then the function recall itself again and again, creating a new stackframe each time
            // until the stack memory is full and JVM stop it and throw an overflow error
            CauseStackOverflow(0);
        } catch (StackOverflowError e) {
            // It detect the stack overflow in memory and stop the recursion and the stack is undone then the catch block is executed
            System.out.println("StackOverflow Error!");
        }
    }
    
    // Take the given Bank account and call deposit function inside it to add 100 to its balance
    public static void CallStack(BankAccount acc) {
        acc.deposit(100);
    }
    
    // It receives the input integer and then recursive itself again with the given value plus one
    // Since there is no stop case, the function will recurse itself infinitely with increasing value
    public static void CauseStackOverflow(int depth) {
        CauseStackOverflow(depth + 1);
    }
    
    public static void primitiveVsObject(double value, BankAccount acc) {
        value += 100;
        acc.deposit(100);
    }

    public static void StringImmutability() {
        String customer = "A";
        customer = customer + " (VIP)";

        StringBuilder log = new StringBuilder("Customer: ");
        log.append(customer);
        System.out.println(log);
    }
}
