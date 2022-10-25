import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class MovableAdapterImpl implements MovableAdapter {

    private Movable luxuryCars;

    @Override
    public double getSpeed() {
        return convertMPtoKP(luxuryCars.getSpeed());
    }

    private double convertMPtoKP(double speed) {
        return speed * 10.0;
    }

}

