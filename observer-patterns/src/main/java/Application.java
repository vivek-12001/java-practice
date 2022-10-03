public class Application {

    public static void main(String[] args) {

        SubscriberOne subscriberOne = new SubscriberOne();
        SubscriberTwo subscriberTwo = new SubscriberTwo();
        SubscriberThree subscriberThree = new SubscriberThree();

        MessagePublisher messagePublisher = new MessagePublisher();
        messagePublisher.attach(subscriberOne);
        messagePublisher.attach(subscriberTwo);

        messagePublisher.notifyUpdate(new Message("Sending first update"));
        messagePublisher.attach(subscriberThree);
        messagePublisher.notifyUpdate(new Message("Sending second update"));

    }

}
