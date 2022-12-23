package atm;

public enum TransactionType {

    CASH_WITHDRAWAL,
    BALANCE_CHECK;

    public static void getAllTransactionTypes() {
        for (TransactionType transactionType : TransactionType.values()) {
            System.out.println("transactionType = " + transactionType);
        }
    }
}
