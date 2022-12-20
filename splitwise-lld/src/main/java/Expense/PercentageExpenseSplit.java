package Expense;

import java.util.List;

public class PercentageExpenseSplit implements ExpenseList {
    @Override
    public void validateSplitRequest(List<Split> splitList, double totalAmount) {
        double totalAmountToBePresent = 0;

        for (Split split: splitList) {
            double currentPercentage = split.getAmountOwe();
            totalAmountToBePresent += currentPercentage;
        }

        if (totalAmountToBePresent != 100) {
            System.out.println("some error");
        }
    }
}
