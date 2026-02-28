public class TestBank {

    public static void main(String[] args) {

        System.out.println("=== Starting OS Bank System ===");

        // Background thread for Concurrency and Priority Scheduling
        Thread backgroundTask = new Thread(() -> {
            System.out.println("[Background] System Backup started...");
            try { Thread.sleep(500); } catch (InterruptedException e) {}
            System.out.println("[Background] System Backup completed.");
        });
        backgroundTask.setPriority(Thread.MIN_PRIORITY); // Set low CPU priority
        backgroundTask.start(); // Run concurrently with main thread

        //Two Bankaccount were created
        BankAccount acc1 = new BankAccount(1000);
        BankAccount acc2 = new BankAccount(500);

        // Transaction Output Logs
        System.out.println("\n--- Initial Account Transactions ---");
        System.out.println("Starting Balances -> Acc1: $" + acc1.getBalance() + " | Acc2: $" + acc2.getBalance());

        System.out.println(">> Processing Deposit: $200 to Acc1...");
        acc1.deposit(200); //acc1 deposit 200 into its bankaccount, current balance = 1200
        
        System.out.println(">> Processing Withdrawal: $100 from Acc1...");
        acc1.withdraw(100);//acc1 withdraw 100 into its bankaccount, current balance = 1100
        
        System.out.println(">> Processing Transfer: $300 from Acc1 to Acc2...");
        acc1.transfer(300, acc2); 
        // acc1 transfer 300 into acc2, acc1's current balance = 800, acc2's current balance = 800
        
        // Print the balances to prove the math worked!
        System.out.println("Final Status after transactions -> Acc1 Balance: $" + acc1.getBalance() + " | Acc2 Balance: $" + acc2.getBalance());
 
        // Callstack is used on acc1 which deposit 100 into acc1, current acc1's balance = 900
        System.out.println("\n--- Testing CallStack ---");
        CallStack(acc1); 
        System.out.println("Acc1 Balance after CallStack: $" + acc1.getBalance());
        
        // Test Heap vs Stack (Pass-by-value vs Pass-by-reference)
        System.out.println("\n--- Testing Primitive vs Object ---");
        double myPrimitive = 50;
        System.out.println("Before -> Primitive: " + myPrimitive + " | Acc1 Balance: $" + acc1.getBalance());
        primitiveVsObject(myPrimitive, acc1);
        System.out.println("After  -> Primitive: " + myPrimitive + " (Unchanged!) | Acc1 Balance: $" + acc1.getBalance() + " (Changed!)");
        
        // Test Heap memory optimization
        System.out.println("\n--- Testing Heap Optimization ---");
        StringImmutability();
        
        // Call the newly extracted Heap Allocator method
        System.out.println("\n--- Testing Heap Reassignment & Garbage Collection ---");
        CallHeap();

        System.out.println("\n--- Testing Stack Overflow ---");
        try {
            // it call the function then the function recall itself again and again, creating a new stackframe each time
            // until the stack memory is full and JVM stop it and throw an overflow error
            CauseStackOverflow(0);
        } catch (StackOverflowError e) {
            // It detect the stack overflow in memory and stop the recursion and the stack is undone then the catch block is executed
            System.out.println("StackOverflow Error! OS prevented crash.");
        }
    }
    
    // Take the given Bank account and call deposit function inside it to add 100 to its balance
    public static void CallStack(BankAccount acc) {
        System.out.println(">> Entering CallStack (Pushed to Stack)");
        acc.deposit(100);
        System.out.println("<< Exiting CallStack (Popped off Stack)");
    }
    
    // It receives the input integer and then recursive itself again with the given value plus one
    // Since there is no stop case, the function will recurse itself infinitely with increasing value
    public static void CauseStackOverflow(int depth) {
        CauseStackOverflow(depth + 1);
    }
    
    // Primitive (value) changes locally on Stack, Object (acc) changes globally in Heap
    public static void primitiveVsObject(double value, BankAccount acc) {
        value += 100;
        acc.deposit(100);
    }

    // StringBuilder modifies one object in Heap, avoiding String garbage collection
    public static void StringImmutability() {
        String customer = "A";
        customer = customer + " (VIP)";

        StringBuilder log = new StringBuilder("Customer: ");
        log.append(customer);
        System.out.println(log);
    }

    // Demonstrates Heap allocation, reassignment, and Garbage Collection
    public static void CallHeap() {
        BankAccount acc3 = new BankAccount(2000);
        BankAccount acc4 = new BankAccount(5000);
        
        // acc4 now points to the same Heap object as acc3
        acc4 = acc3; 
        System.out.println("After Reassignment (acc4 = acc3), Acc4 Balance becomes: $" + acc4.getBalance());
        
        acc3.deposit(500); // Deposit to acc3, which also affects acc4 since they point to the same object
        System.out.println("Deposited $500 to Acc3. Acc4 Balance is also: $" + acc4.getBalance() + " (Because both references point to the same Heap object)");
        
        // Nullifying references to trigger Garbage Collection
        acc3 = null;
        acc4 = null;
        System.out.println("References nullified (acc3 = null, acc4 = null). Heap object is now orphaned.");
        System.gc(); // Manually requesting the JVM to run the Garbage Collector
        System.out.println("Garbage Collection triggered to clean up orphaned objects.");
    }
}
