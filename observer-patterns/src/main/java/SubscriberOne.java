public class SubscriberOne implements Observer {
    @Override
    public void update(Message message) {
        System.out.println(" [Subscriber one] Message received : " + message.getMessageContent());
    }
}
