public class SubscriberThree implements Observer {
    @Override
    public void update(Message message) {
        System.out.println(" [Subscriber three] Message received : " + message.getMessageContent());
    }
}
