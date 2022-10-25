package states;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import vendingmachine.VendingMachine;

@AllArgsConstructor
@NoArgsConstructor
public class NoCoinInsertedState implements State {

    VendingMachine vendingMachine;

    public void NoCoinInsertedState (VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(double amount) {
        vendingMachine.setAmount(amount);
        vendingMachine.setCurVendingMachineState(vendingMachine.getCoinInsertedState());
    }

    @Override
    public void pressButton(int aisleNumber) {
        throw new IllegalStateException("No coin is Inserted...!!!");
    }

    @Override
    public void dispense(int aisleNumber) {
        throw new IllegalStateException("No coin is Inserted...!!!");
    }
}
