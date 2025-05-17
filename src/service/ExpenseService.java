package service;

import manager.BalanceManager;
import model.Expense;
import model.Split;
import model.SplitType;
import model.User;
import strategy.EqualSplitStrategy;
import strategy.ExactSplitStrategy;
import strategy.PercentSplitStrategy;
import strategy.SplitStrategy;

import java.util.List;

//This class acts as the main application/service layer responsible for:
//Adding expenses paid by a user and splitting them among a group of users
//Calculating how much each user owes using a split strategy
//Updating the balances between users via the BalanceManager
//Displaying current balances between users
//It orchestrates the core business logic of the Splitwise system.

public class ExpenseService {

//    Instantiates the BalanceManager that tracks debts between users.
//    This manager stores and updates who owes whom and how much.
    private final BalanceManager balanceManager=new BalanceManager();

//    Determine the splitting logic:
//    Calls getStrategy(splitType) to get the right SplitStrategy (e.g., equal split).
//    Calculate splits:
//    strategy.calculateSplits(amount, users) returns a list of Split objects.
//    Each Split tells how much a user owes.
//    Create an Expense object:
//    Holds the info about who paid, the amount, the splits, and the split type.
//    Update balances:
//    Iterates through the splits.
//    For each user other than the payer, calls:
    public void addExpense(User paidBy, double amount, List<User> users, SplitType splitType,List<Double> values){
        SplitStrategy strategy=getStrategy(splitType,values);
        List<Split> splits=strategy.calculateSplits(amount, users);
        Expense expense=new Expense(paidBy,amount,splits,splitType);
        for(Split split:splits){
            if(!split.getUser().getId().equals(paidBy.getId())){
                balanceManager.addTransaction(split.getUser().getId(), paidBy.getId(), split.getAmount());
            }
        }
    }

    private SplitStrategy getStrategy(SplitType splitType,List<Double> values){
        switch (splitType){
            case EQUAL:
                return new EqualSplitStrategy();
            case EXACT:
                return new ExactSplitStrategy(values);
            case PERCENT:
                return new PercentSplitStrategy(values);
            default:
                throw new IllegalArgumentException("Invalid SplitType");
        }
    }

    public void showBalance(){
        balanceManager.showBalance();
    }
}
