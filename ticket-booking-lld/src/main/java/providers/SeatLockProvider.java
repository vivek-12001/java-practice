package providers;

import exceptions.SeatTemporaryUnavailableException;
import model.Seat;
import model.Show;

import java.util.List;

public interface SeatLockProvider {

    void lockSeats(Show show, List<Seat> seats, String user) throws SeatTemporaryUnavailableException;
    void unlockSeats(Show show, List<Seat> seats, String user);
    boolean validate(Show show, Seat seat, String user);

    List<Seat> getLockedSeats(Show show);
}
