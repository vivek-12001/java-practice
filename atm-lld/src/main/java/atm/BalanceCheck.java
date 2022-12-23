package atm;

public class BalanceCheck extends ATMStates {

    @Override
    public void displayBalance(Card card, ATM atm) {
        System.out.println("Bank balance = " + card.getBankBalance());
        exit(atm);
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
