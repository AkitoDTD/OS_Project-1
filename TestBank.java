public class TestBank {

    public static void main(String[] args) {

        BankAccount acc1 = new BankAccount(1000);
        BankAccount acc2 = new BankAccount(500);

        acc1.deposit(200);
        acc1.withdraw(100);
        acc1.transfer(300, acc2);

        CallStack(acc1);
        primitiveVsObject(50, acc1);
        StringImmutability();

        try {
            CauseStackOverflow(0);
        } catch (StackOverflowError e) {
            System.out.println("StackOverflow Error!");
        }
    }

    public static void CallStack(BankAccount acc) {
        acc.deposit(100);
    }

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
