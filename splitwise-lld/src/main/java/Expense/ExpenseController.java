package Expense;

import Balance.BalanceSheetController;
import User.User;

import java.util.List;

public class ExpenseController {

    BalanceSheetController balanceSheetController;

    public ExpenseController() {
        balanceSheetController = new BalanceSheetController();
    }

    public Expense createExpense(String expenseId, String expenseDescription, double expenseAmount, User user,
                                 ExpenseSplitType splitType, List<Split> splitDetails) {
        ExpenseList expenseList = ExpenseSplitFactory.getSplitType(splitType);
        expenseList.validateSplitRequest(splitDetails, expenseAmount);

        Expense expense = new Expense(expenseId, expenseDescription, expenseAmount, user, splitType, splitDetails);
        balanceSheetController.updateUserExpenseSheet(user, splitDetails, expenseAmount);

        return expense;
    }
}
