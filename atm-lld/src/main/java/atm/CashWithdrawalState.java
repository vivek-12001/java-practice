package atm;

import amountWithdrawal.CashWithdrawalProcessor;
import amountWithdrawal.FiveHundredWithdrawal;
import amountWithdrawal.OneHundredWithdrawal;
import amountWithdrawal.TwoThousandWithdrawal;

public class CashWithdrawalState extends ATMStates {

    @Override
    public void cashWithdrawal(Card card, ATM atm, int withdrawalAmount) {
        if (atm.getAtmBalance() <= withdrawalAmount) {
            System.out.println("sorry... no more money in atm");
            exit(atm);
        } else if (card.getBankBalance() <= withdrawalAmount) {
            System.out.println("sorry... you dont have that much money");
            exit(atm);
        } else {
            card.deductBankBalance(withdrawalAmount);
            atm.deductBalance(withdrawalAmount);

            CashWithdrawalProcessor cashWithdrawalProcessor = new TwoThousandWithdrawal(new FiveHundredWithdrawal(new OneHundredWithdrawal(null)));

            cashWithdrawalProcessor.withdraw(atm, withdrawalAmount);

            exit(atm);
        }
    }

    @Override
    public void exit(ATM atm) {
        returnCard();
        System.out.println("exiting...");
        atm.setAtmCurrentStates(new IdleState());
    }

    @Override
    public void returnCard() {
        System.out.println("please collect your card...");
    }
}
