package atm;

import user.UserBankAccount;

public class Card {

    private int cardNumber;
    private int cvv;
    private int holderName;
    private int expiryDate;
    private int PIN_NUMBER = 1111;
    UserBankAccount userBankAccount;

    public int getBankBalance() {
        return userBankAccount.balance;
    }

    public boolean isPinCorrectlyEntered(int pin) {
        if (pin == PIN_NUMBER)
            return true;
        return false;
    }

    public void deductBankBalance(int amount) {
        userBankAccount.withdrawalBalance(amount);
    }

    public void setUserBankAccount(UserBankAccount userBankAccount) {
        this.userBankAccount = userBankAccount;
    }
}
