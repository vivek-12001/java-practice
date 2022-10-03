public class Application {
    public static void main(String[] args) {
        DeliveryContext deliveryContext = new DeliveryContext(new Acknowledged(), 123);
        deliveryContext.update();
        deliveryContext.update();
        deliveryContext.update();
        deliveryContext.update();
        deliveryContext.update();
    }
}
