import inventory.Product;
import vendingmachine.VendingMachine;

public class Application {
    public static void main(String[] args) {

        VendingMachine vendingMachine = new VendingMachine();

        Product parle = new Product("PARLE", 1, 5.0);
        Product marie = new Product("marie", 2, 10.0);
        Product dreamCream = new Product("dreamCream", 3, 25.0);

        for (int i=1;i<=3;i++)
            vendingMachine.addProduct(parle);

        for (int i=1;i<=3;i++)
            vendingMachine.addProduct(marie);

        for (int i=1;i<=3;i++)
            vendingMachine.addProduct(dreamCream);

        vendingMachine.getInventory().getProductAt(1);

        vendingMachine.insertCoins(8.0);
        vendingMachine.insertCoins(3.0);
        vendingMachine.pressButton(1);

        vendingMachine.insertCoins(8.0);
        vendingMachine.pressButton(2);

    }
}
