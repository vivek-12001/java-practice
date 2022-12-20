package Expense;

import java.util.List;

public interface ExpenseList {

    public void validateSplitRequest(List<Split> splitList, double totalAmount);
}
