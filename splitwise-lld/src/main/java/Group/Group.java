package Group;

import Expense.Expense;
import Expense.ExpenseSplitType;
import Expense.Split;
import Expense.ExpenseController;
import User.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Group {

    String groupId;
    String groupName;
    List<User> groupMembers;
    List<Expense> expenseList;
    ExpenseController expenseController;

    public Group() {
        groupMembers = new ArrayList<>();
        expenseList = new ArrayList<>();
        expenseController = new ExpenseController();
    }

    public void addMembers(User user) {
        groupMembers.add(user);
    }

    public Expense createExpense(String expenseId, String expenseDescription, double expenseAmount, User user,
                                 ExpenseSplitType splitType, List<Split> splitDetails) {
        Expense expense = expenseController.createExpense(expenseId, expenseDescription, expenseAmount, user, splitType, splitDetails);
        expenseList.add(expense);
        return expense;
    }
}
