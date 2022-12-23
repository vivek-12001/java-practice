package atm;

public class SelectOperationType extends ATMStates {

    public SelectOperationType() {
        showAllOperations();
    }

    private void showAllOperations() {
        System.out.println("Printing all operations...");
        TransactionType.getAllTransactionTypes();
    }

    @Override
    public void selectOperation(Card card, ATM atm, TransactionType transactionType) {
        switch (transactionType) {
            case CASH_WITHDRAWAL:
                atm.setAtmCurrentStates(new CashWithdrawalState());
                break;
            case BALANCE_CHECK:
                atm.setAtmCurrentStates(new BalanceCheck());
                break;
            default:
                System.out.println("Invalid option");
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
