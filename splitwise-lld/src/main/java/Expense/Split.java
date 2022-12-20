package Expense;

import User.User;
import lombok.Data;

@Data
public class Split {

    User user;
    double amountOwe;

    public Split(User user, double amountOwe) {
        this.user = user;
        this.amountOwe = amountOwe;
    }
}
