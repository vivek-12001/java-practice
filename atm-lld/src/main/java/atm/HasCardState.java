package atm;

public class HasCardState extends ATMStates {

    public HasCardState() {
        System.out.println("enter your pin number : ");
    }

    @Override
    public void authenticatePin(Card card, ATM atm, int pinNo) {
        boolean isPinCorrectlyEntered = card.isPinCorrectlyEntered(pinNo);

        if (isPinCorrectlyEntered) {
            atm.setAtmCurrentStates(new SelectOperationType());
        } else {
            System.out.println("Pin is not correctly entered");
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
