package manager;

import java.util.HashMap;
import java.util.Map;

public class BalanceManager {
//    Outer map key: String paidBy user ID
//    Inner map key: String paidTo user ID
//    Inner map value: Double, amount User paidBy owes User paidTo
//    This nested map helps track debts pairwise between all users.
    private final Map<String,Map<String,Double>> balance=new HashMap<>();

   // This method updates balances when a new expense transaction happens.
    public void addTransaction(String paidBy,String paidTo,double amount){
       // If either user has no existing map, create an empty map for them.
        balance.putIfAbsent(paidBy,new HashMap<>());
        balance.putIfAbsent(paidTo,new HashMap<>());

//        This line does two things:
//        User paidBy owes User paidTo less:
//        Subtract amount from what paidBy owes paidTo (usually decreases debt because paidBy paid).
//      User paidTo owes User paidBy more:
//        Add amount to what paidTo owes paidBy (other side of the debt).
//      This dual update keeps the debt info consistent in both directions.
        balance.get(paidBy).put(paidTo,balance.get(paidBy).getOrDefault(paidTo,0.0)-amount);
        balance.get(paidTo).put(paidBy,balance.get(paidTo).getOrDefault(paidBy,0.0)+amount);
    }

    //This method prints all debts in human-readable format, but only debts that are negative (user owes money).
//    For each user pair (user1, user2), if amount < 0 it means user1 owes money to user2.
//    The amount is printed as a positive value with a clear message.
    public void showBalance(){
        for(String user1: balance.keySet()){
            for(Map.Entry<String,Double> entry:balance.get(user1).entrySet()){
                String user2= entry.getKey();
                double amount= entry.getValue();
                if(amount<0){
                    System.out.printf("%s owes %s : %.2f%n",user1,user2,-amount);
                }
            }
        }
    }
}

//If Alice (u1) pays ₹1000 split between Bob (u2) and Charlie (u3), after transactions:
//Bob’s debt map: balances[u2][u1] = +500 (Bob owes Alice ₹500)
//Alice’s debt map: balances[u1][u2] = -500 (Alice is owed ₹500 by Bob)
//Printing:
//For Bob: amount to Alice is positive (Bob owes Alice)
//For Alice: amount to Bob is negative (Alice is owed money, so don't print)


