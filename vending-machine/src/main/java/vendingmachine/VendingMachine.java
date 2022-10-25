package vendingmachine;

import inventory.Inventory;
import inventory.Product;
import lombok.Data;
import states.CoinInsertedState;
import states.DispenseState;
import states.NoCoinInsertedState;
import states.State;

@Data
public class VendingMachine {

    private NoCoinInsertedState noCoinInsertedState;
    private CoinInsertedState coinInsertedState;
    private DispenseState dispenseState;
    private State curVendingMachineState;
    private Inventory inventory;
    private Product product;
    private double amount;

    public VendingMachine () {
        noCoinInsertedState = new NoCoinInsertedState(this);
        coinInsertedState = new CoinInsertedState(this);
        dispenseState = new DispenseState(this);
        curVendingMachineState = noCoinInsertedState;
        amount = 0.0;
        inventory = new Inventory(2);
    }

    public boolean hasSufficientAmount(double expectedAmount) {
        return expectedAmount <= this.amount;
    }

    public void insertCoins(double insertedAmount) {
        System.out.println("insertedAmount = " + insertedAmount);
        this.curVendingMachineState.insertCoin(insertedAmount);
    }

    public void pressButton(int aisleNumber) {
        this.curVendingMachineState.pressButton(aisleNumber);
        this.curVendingMachineState.dispense(aisleNumber);
    }

    public void addProduct(Product product) {
        try {
            this.getInventory().addProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


