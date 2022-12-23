package amountWithdrawal;

import atm.ATM;

public class OneHundredWithdrawal extends CashWithdrawalProcessor {

    public OneHundredWithdrawal(CashWithdrawalProcessor cashWithdrawalProcessor) {
        super(cashWithdrawalProcessor);
    }

    @Override
    public void withdraw(ATM atm, int withdrawalAmount) {

        int required = withdrawalAmount % 100;
        int remaining = withdrawalAmount / 100;

        if (required <= atm.getNoOfOneHundredNotes()) {
            atm.deductOneHundredNotes(required);
        } else if (required > atm.getNoOfOneHundredNotes()) {
            atm.deductOneHundredNotes(atm.getNoOfOneHundredNotes());
            remaining = remaining + (required - atm.getNoOfOneHundredNotes()) * 100;
        }

        if (remaining != 0) {
            super.withdraw(atm, remaining);
        }
    }
}
