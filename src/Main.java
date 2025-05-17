import model.Split;
import model.SplitType;
import model.User;
import service.ExpenseService;
import strategy.EqualSplitStrategy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ExpenseService expenseService=new ExpenseService();

        User u1=new User("u1","Alice");
        User u2=new User("u2","Bob");
        User u3=new User("u3","Charlie");
        User u4=new User("u4","Adam");

        Map<String,User> users=new HashMap<>();
        users.put(u1.getId(), u1);
        users.put(u2.getId(),u2);
        users.put(u3.getId(),u3);
        users.put(u4.getId(),u4);

        // Alice pays ₹1000 split equally among Bob, Charlie, Adam
        expenseService.addExpense(u1,1000, Arrays.asList(u2,u3,u4), SplitType.EQUAL,null);
        expenseService.showBalance();
        expenseService.addExpense(u1, 1000, Arrays.asList(u2, u3, u4),
                SplitType.EXACT, Arrays.asList(300.0, 300.0, 400.0));
        expenseService.showBalance();
        // Example for PERCENT split: 1000 → 40%, 30%, 30%
        expenseService.addExpense(u1, 1000, Arrays.asList(u2, u3, u4),
                SplitType.PERCENT, Arrays.asList(40.0, 30.0, 30.0));
        expenseService.showBalance();
        testEqualSplit();
        }

    private static void testEqualSplit() {
        System.out.println("Running testEqualSplit...");
        User u1 = new User("u1", "Alice");
        User u2 = new User("u2", "Bob");
        User u3 = new User("u3", "Charlie");

        EqualSplitStrategy strategy = new EqualSplitStrategy();
        List<Split> splits = strategy.calculateSplits(600, Arrays.asList(u1, u2, u3));

        boolean passed = splits.size() == 3 &&
                Math.abs(splits.get(0).getAmount() - 200.0) < 0.01 &&
                Math.abs(splits.get(1).getAmount() - 200.0) < 0.01 &&
                Math.abs(splits.get(2).getAmount() - 200.0) < 0.01;

        if (passed) System.out.println("✅ testEqualSplit passed");
        else System.out.println("❌ testEqualSplit failed");
    }

    }
