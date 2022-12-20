package User;

import Balance.UserExpenseBalanceSheet;
import lombok.Data;

@Data
public class User {

    public String userId;
    public String userName;
    UserExpenseBalanceSheet userExpenseBalanceSheet;

    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        userExpenseBalanceSheet = new UserExpenseBalanceSheet();
    }
}
