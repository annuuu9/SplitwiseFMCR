package model;

import java.util.List;

//Captures a single expense's core details.
//paidBy → Who paid the amount
//splits → Who owes how much
//splitType → How to interpret the split (equal, etc.)
//This is what gets created every time someone pays a bill.

public class Expense {
    private User paidBy;
    private double amount;
    private List<Split> splits;
    private SplitType splitType;

    public Expense(User paidBy,double amount,List<Split> splits,SplitType splitType){
        this.paidBy=paidBy;
        this.amount=amount;
        this.splits=splits;
        this.splitType=splitType;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public double getAmount() {
        return amount;
    }

    public List<Split> getSplits() {
        return splits;
    }
}
