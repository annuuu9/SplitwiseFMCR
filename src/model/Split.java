package model;

//Represents how much each user owes in a split
//Each Split instance = 1 user's share in a group expense.
//Used inside Expense as a list.

public class Split {
    private User user;
    private double amount;

    public Split(User user,double amount){
        this.user=user;
        this.amount=amount;
    }

    public User getUser() {
        return user;
    }

    public double getAmount() {
        return amount;
    }
}
