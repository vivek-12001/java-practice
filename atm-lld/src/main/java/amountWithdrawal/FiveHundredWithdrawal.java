package amountWithdrawal;

import atm.ATM;

public class FiveHundredWithdrawal extends CashWithdrawalProcessor {

    public FiveHundredWithdrawal(CashWithdrawalProcessor cashWithdrawalProcessor) {
        super(cashWithdrawalProcessor);
    }

    @Override
    public void withdraw(ATM atm, int withdrawalAmount) {

        int required = withdrawalAmount % 500;
        int remaining = withdrawalAmount / 500;

        if (required <= atm.getNoOfFiveHundredNotes()) {
            atm.deductFiveHundredNotes(required);
        } else if (required > atm.getNoOfFiveHundredNotes()) {
            atm.deductFiveHundredNotes(atm.getNoOfFiveHundredNotes());
            remaining = remaining + (required - atm.getNoOfFiveHundredNotes()) * 500;
        }

        if (remaining != 0) {
            super.withdraw(atm, remaining);
        }
    }
}
