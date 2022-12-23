package atm;

public class IdleState extends ATMStates {

    @Override
    public void insertCard(Card card, ATM atm) {
        System.out.println("card has been inserted");
        atm.setAtmCurrentStates(new HasCardState());
    }
}
