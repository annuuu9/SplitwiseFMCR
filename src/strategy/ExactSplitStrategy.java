package strategy;

import model.Split;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class ExactSplitStrategy implements SplitStrategy{
    private final List<Double> exactAmounts;

    public ExactSplitStrategy(List<Double> exactAmounts){
        this.exactAmounts=exactAmounts;
    }

    @Override
    public List<Split> calculateSplits(double amount,List<User> users){
        if(users.size()!=exactAmounts.size()){
            throw new IllegalArgumentException("mismatch between user and exact amounts");
        }
        double sum=0.0;
        for(double amt:exactAmounts){
            sum+=amt;
        }
        if(Math.abs(sum-amount)>0.01){
            throw new IllegalArgumentException("exact amount do not equal to sum");
        }
        List<Split> splits=new ArrayList<>();
        for(int i=0;i<users.size();i++){
            splits.add(new Split(users.get(i),exactAmounts.get(i)));
        }
        return splits;
    }
}
