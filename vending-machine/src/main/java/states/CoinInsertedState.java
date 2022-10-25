package states;

import inventory.Inventory;
import inventory.Product;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import vendingmachine.VendingMachine;


@AllArgsConstructor
@NoArgsConstructor
public class CoinInsertedState implements State {

    VendingMachine vendingMachine;

    @Override
    public void insertCoin(double amount) {
        vendingMachine.setAmount(vendingMachine.getAmount() + amount);
    }

    @Override
    public void pressButton(int aisleNumber) {
        Inventory inventory = vendingMachine.getInventory();
        Product product = inventory.getProductAt(aisleNumber);

        if (!vendingMachine.hasSufficientAmount(product.getProductPrice())) {
            throw new IllegalStateException("insufficient amount to buy");
        }
        if (!inventory.checkIfProductAvailable(product.getProductId())) {
            throw new IllegalStateException("no products available");
        }

        vendingMachine.setCurVendingMachineState(vendingMachine.getDispenseState());
    }

    @Override
    public void dispense(int aisleNumber) {
        throw new IllegalStateException("incorrect state...!!!");
    }
}
