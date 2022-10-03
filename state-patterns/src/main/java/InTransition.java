public class InTransition implements PackageState {
    @Override
    public void updateState(DeliveryContext deliveryContext) {
        System.out.println("Package is in Transition state");
        deliveryContext.setCurrentState(new OutForDelivery());
    }
}
