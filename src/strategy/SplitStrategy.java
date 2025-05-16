package strategy;

import model.Split;
import model.User;
import java.util.List;

//An interface that lets you plug in different splitting logics.
//Why it's powerful:
//Enables Open/Closed Principle
//You can easily add ExactSplitStrategy, PercentSplitStrategy later

public interface SplitStrategy {
    List<Split> calculateSplits(double amount, List<User> users);
}
