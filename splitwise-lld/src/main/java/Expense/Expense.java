package Expense;

import User.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Expense {

    String expenseId;
    String expenseDescription;
    double expenseAmount;
    User user;
    ExpenseSplitType splitType;
    List<Split> splitList = new ArrayList<>();
}
