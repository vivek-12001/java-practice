package Balance;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class UserExpenseBalanceSheet {

    Map<String, Balance> userVsBalance;
    double totalExpense;
    double totalPayment;
    double totalYouOwe;
    double totalGetBack;

    public UserExpenseBalanceSheet() {
        userVsBalance = new HashMap<>();
        totalExpense = 0;
        totalYouOwe = 0;
        totalGetBack = 0;
    }
}
