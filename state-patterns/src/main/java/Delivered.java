public class Delivered implements PackageState {
    @Override
    public void updateState(DeliveryContext deliveryContext) {
        System.out.println("Package is delivered");
    }
}
