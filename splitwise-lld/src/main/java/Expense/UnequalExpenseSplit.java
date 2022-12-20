package Expense;

import java.util.List;

public class UnequalExpenseSplit implements ExpenseList {
    @Override
    public void validateSplitRequest(List<Split> splitList, double totalAmount) {

        double totalAmountToBePresent = 0;

        for (Split split: splitList) {
            double currentSplit = split.getAmountOwe();
            totalAmountToBePresent += currentSplit;
        }

        if (totalAmountToBePresent != totalAmount) {
            System.out.println("Error split value not matching with total amount spent");
        }
    }
}
