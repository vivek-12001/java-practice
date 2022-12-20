package Expense;

import java.util.List;

public class EqualExpenseSplit implements ExpenseList {
    @Override
    public void validateSplitRequest(List<Split> splitList, double totalAmount) {

        double totalAmountToBePresent = totalAmount / splitList.size();

        for (Split split: splitList) {
            if (split.getAmountOwe() != totalAmountToBePresent) {
                System.out.println("split is not divided equally");
            }
        }
    }
}
