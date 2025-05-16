package strategy;

import model.Split;
import model.User;
import java.util.ArrayList;
import java.util.List;

//An implementation of SplitStrategy that divides amount equally.
//Example:
//₹1000 split between 3 users → ₹333.33 each
//Used when user says "split equally"

public class EqualSplitStrategy implements SplitStrategy {
    @Override
    public List<Split> calculateSplits(double amount, List<User> users){
        List<Split> splits=new ArrayList<>();
        double equalAmount=amount/users.size();
        for(User us:users){
            splits.add(new Split(us,equalAmount));
        }
        return splits;
    }
}
