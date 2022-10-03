public class OutForDelivery implements PackageState {
    @Override
    public void updateState(DeliveryContext deliveryContext) {
        System.out.println("Package is out for delivery");
        deliveryContext.setCurrentState(new Delivered());
    }
}
