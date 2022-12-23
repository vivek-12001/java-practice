package atm;

import user.User;
import user.UserBankAccount;

public class ATMRoom {

    ATM atm;
    User user;

    public static void main(String[] args) {

        ATMRoom atmRoom = new ATMRoom();
        atmRoom.initialize();

        atmRoom.atm.printCurrentATMStatus();
        atmRoom.atm.getAtmCurrentStates().insertCard(atmRoom.user.getCard(), atmRoom.atm);
        atmRoom.atm.getAtmCurrentStates().authenticatePin(atmRoom.user.getCard(), atmRoom.atm, 1111);
        /*atmRoom.atm.getAtmCurrentStates().selectOperation(atmRoom.user.getCard(), atmRoom.atm, TransactionType.BALANCE_CHECK);
        atmRoom.atm.getAtmCurrentStates().displayBalance(atmRoom.user.getCard(), atmRoom.atm);*/
        atmRoom.atm.getAtmCurrentStates().selectOperation(atmRoom.user.getCard(), atmRoom.atm, TransactionType.CASH_WITHDRAWAL);
        atmRoom.atm.getAtmCurrentStates().cashWithdrawal(atmRoom.user.getCard(), atmRoom.atm, 2600);
        atmRoom.atm.printCurrentATMStatus();
    }

    private void initialize() {

        //create ATM
        atm = ATM.getAtmObject();
        atm.setAtmBalance(2000, 1, 2, 5);

        //create User
        this.user = createUser();
    }

    private User createUser() {

        User user = new User();
        user.setCard(createCard());
        return user;
    }

    private Card createCard() {

        Card card = new Card();
        card.setUserBankAccount(createBankAccount());
        return card;
    }

    private UserBankAccount createBankAccount() {

        UserBankAccount bankAccount = new UserBankAccount();
        bankAccount.balance = 3000;
        return bankAccount;
    }
}
