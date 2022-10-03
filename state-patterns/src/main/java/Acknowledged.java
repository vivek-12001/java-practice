public class Acknowledged implements PackageState {
    @Override
    public void updateState(DeliveryContext deliveryContext) {
        System.out.println("Package is acknowledge...!!!");
        deliveryContext.setCurrentState(new Shipped());
    }
}
