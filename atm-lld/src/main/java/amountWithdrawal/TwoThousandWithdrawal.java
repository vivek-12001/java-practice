package amountWithdrawal;

import atm.ATM;

public class TwoThousandWithdrawal extends CashWithdrawalProcessor {

    public TwoThousandWithdrawal(CashWithdrawalProcessor cashWithdrawalProcessor) {
        super(cashWithdrawalProcessor);
    }

    @Override
    public void withdraw(ATM atm, int withdrawalAmount) {

        int required = withdrawalAmount % 2000;
        int remaining = withdrawalAmount / 2000;

        if (required <= atm.getNoOfTwoThousandNotes()) {
            atm.deductTwoThousandNotes(required);
        } else if (required > atm.getNoOfTwoThousandNotes()) {
            atm.deductTwoThousandNotes(atm.getNoOfTwoThousandNotes());
            remaining = remaining + (required - atm.getNoOfTwoThousandNotes()) * 2000;
        }

        if (remaining != 0) {
            super.withdraw(atm, remaining);
        }
    }
}
