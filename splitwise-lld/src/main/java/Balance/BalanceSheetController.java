package Balance;

import Expense.Split;
import User.User;

import java.util.List;
import java.util.Map;

public class BalanceSheetController {

    public void showBalanceSheetOfUser(User user) {

        System.out.println("**************************");
        System.out.println("Balance sheet of user : " + user.getUserName());

        UserExpenseBalanceSheet userExpenseBalanceSheet = user.getUserExpenseBalanceSheet();

        System.out.println("Total Expense = " + userExpenseBalanceSheet.getTotalExpense());
        System.out.println("Total Payment you should get back = " + userExpenseBalanceSheet.getTotalGetBack());
        System.out.println("Total payment you owe = " + userExpenseBalanceSheet.getTotalYouOwe());

        for (Map.Entry<String, Balance> entry :
                userExpenseBalanceSheet.getUserVsBalance().entrySet()) {
            System.out.println("User Id = " + entry.getKey() + " Balance you owe = " + entry.getValue().getAmountOwe()
                    + " Balance get back = " + entry.getValue().getAmountGetBack());
        }
    }

    public void updateUserExpenseSheet(User expensePaidBy, List<Split> splitList, double totalAmount) {

        UserExpenseBalanceSheet userExpenseBalanceSheet = expensePaidBy.getUserExpenseBalanceSheet();
        userExpenseBalanceSheet.setTotalExpense(totalAmount);

        for (Split split : splitList) {
            User userOwe = split.getUser();
            UserExpenseBalanceSheet oweUserExpenseBalanceSheet = userOwe.getUserExpenseBalanceSheet();
            double amountOwe = split.getAmountOwe();

            if (expensePaidBy.getUserId().equals(userOwe.getUserId())) {
                userExpenseBalanceSheet.setTotalExpense(userExpenseBalanceSheet.getTotalExpense() + amountOwe);
            } else {
                userExpenseBalanceSheet.setTotalGetBack(userExpenseBalanceSheet.getTotalGetBack() + amountOwe);

                Balance userOweBalance;
                if (userExpenseBalanceSheet.getUserVsBalance().containsKey(userOwe.getUserId())) {
                    userOweBalance = userExpenseBalanceSheet.getUserVsBalance().get(userOwe.getUserId());
                } else {
                    userOweBalance = new Balance();
                    userExpenseBalanceSheet.getUserVsBalance().put(userOwe.getUserId(), userOweBalance);
                }
                userOweBalance.setAmountGetBack(userOweBalance.getAmountGetBack() + amountOwe);

                oweUserExpenseBalanceSheet.setTotalYouOwe(oweUserExpenseBalanceSheet.getTotalYouOwe() + amountOwe);
                oweUserExpenseBalanceSheet.setTotalExpense(oweUserExpenseBalanceSheet.getTotalExpense() + amountOwe);

                Balance userPaidBalance;
                if (oweUserExpenseBalanceSheet.getUserVsBalance().containsKey(userOwe.getUserId())) {
                    userPaidBalance = oweUserExpenseBalanceSheet.getUserVsBalance().get(userOwe.getUserId());
                } else {
                    userPaidBalance = new Balance();
                    oweUserExpenseBalanceSheet.getUserVsBalance().put(userOwe.getUserId(), userPaidBalance);
                }
                userPaidBalance.setAmountOwe(userOweBalance.getAmountOwe() + amountOwe);
            }
        }

    }
}

