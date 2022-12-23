package atm;

public abstract class ATMStates {

    public void insertCard(Card card, ATM atm) {
    }

    public void authenticatePin(Card card, ATM atm, int pinNo) {
    }

    public void selectOperation(Card card, ATM atm, TransactionType transactionType) {
    }

    public void cashWithdrawal(Card card, ATM atm, int withdrawalAmount) {
    }

    public void displayBalance(Card card, ATM atm) {
    }

    public void returnCard() {
    }

    public void exit(ATM atm) {
    }
}
