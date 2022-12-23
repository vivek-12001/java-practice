package amountWithdrawal;

import atm.ATM;

public abstract class CashWithdrawalProcessor {

    CashWithdrawalProcessor cashWithdrawalProcessor;

    CashWithdrawalProcessor(CashWithdrawalProcessor cashWithdrawalProcessor) {
        this.cashWithdrawalProcessor = cashWithdrawalProcessor;
    }

    public void withdraw(ATM atm, int withdrawalAmount) {
        if (cashWithdrawalProcessor != null) {
            cashWithdrawalProcessor.withdraw(atm, withdrawalAmount);
        }
    }

}
