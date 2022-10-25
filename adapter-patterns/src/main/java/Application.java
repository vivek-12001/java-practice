public class Application {

    public static void main(String[] args) {

        Movable movable = new BugattiVeyron();
        MovableAdapter movableAdapter = new MovableAdapterImpl(movable);

        double mpToKm = movableAdapter.getSpeed();
        System.out.println(mpToKm);

    }

}
