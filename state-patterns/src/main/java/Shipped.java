public class Shipped implements PackageState {
    @Override
    public void updateState(DeliveryContext deliveryContext) {
        System.out.println("Package is shipped");
        deliveryContext.setCurrentState(new InTransition());
    }
}
