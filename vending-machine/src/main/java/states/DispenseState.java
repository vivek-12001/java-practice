package states;

import inventory.Inventory;
import inventory.Product;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import vendingmachine.VendingMachine;

@AllArgsConstructor
@NoArgsConstructor
public class DispenseState implements State {

    VendingMachine vendingMachine;

    public void DispenseState (VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(double amount) {
        throw new IllegalStateException("Product getting dispensed...!!!");
    }

    @Override
    public void pressButton(int aisleNumber) {
        throw new IllegalStateException("Product getting dispensed...!!!");
    }

    @Override
    public void dispense(int aisleNumber) {
        Inventory inventory = vendingMachine.getInventory();
        Product product = inventory.getProductAt(aisleNumber);

        inventory.deductProductCount(aisleNumber);

        double change = vendingMachine.getAmount() - product.getProductPrice();
        vendingMachine.setAmount(0);
        vendingMachine.setCurVendingMachineState(vendingMachine.getNoCoinInsertedState());

        System.out.println("Product " + product + " is getting dispensed. Also, please collect change of = " + change);
    }
}
