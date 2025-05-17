package strategy;

import model.Split;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class PercentSplitStrategy implements SplitStrategy {

    private final List<Double> percents;

    public PercentSplitStrategy(List<Double> percents) {
        this.percents = percents;
    }

    @Override
    public List<Split> calculateSplits(double amount, List<User> users) {
        if (users.size() != percents.size()) {
            throw new IllegalArgumentException("Mismatch between users and percentages.");
        }

        double total = 0;
        for (double p : percents) total += p;
        if (Math.abs(total - 100.0) > 0.01) {
            throw new IllegalArgumentException("Percentages must sum to 100.");
        }

        List<Split> splits = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            double splitAmount = (percents.get(i) * amount) / 100.0;
            splits.add(new Split(users.get(i), splitAmount));
        }

        return splits;
    }
}
