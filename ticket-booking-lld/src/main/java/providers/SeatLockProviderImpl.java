package providers;

import com.google.common.collect.ImmutableList;
import exceptions.SeatTemporaryUnavailableException;
import lombok.NonNull;
import model.Seat;
import model.SeatLock;
import model.Show;

import java.util.*;

public class SeatLockProviderImpl implements SeatLockProvider {

    private final Integer lockTimeout;
    private final Map<Show, Map<Seat, SeatLock>> seatLockMap;

    public SeatLockProviderImpl(@NonNull final Integer lockTimeout) {
        this.lockTimeout = lockTimeout;
        this.seatLockMap = new HashMap<>();
    }

    @Override
    synchronized public void lockSeats(@NonNull final Show show, @NonNull final List<Seat> seats, @NonNull final String user) throws SeatTemporaryUnavailableException {

        for (Seat seat : seats) {
            if (isSeatLocked(show, seat)) {
                throw new SeatTemporaryUnavailableException("Seat is temporarily locked by some other user");
            }
        }

        for (Seat seat : seats) {
            lockSeat(show, seat, user, lockTimeout);
        }
    }

    private void lockSeat(Show show, Seat seat, String user, Integer lockTimeout) {
        if (!seatLockMap.containsKey(show)) {
            seatLockMap.put(show, new HashMap<>());
        }

        SeatLock seatLock = new SeatLock(seat, show, lockTimeout, new Date(), user);
        seatLockMap.get(show).put(seat, seatLock);
    }

    private boolean isSeatLocked(Show show, Seat seat) {
        return seatLockMap.containsKey(show) && seatLockMap.get(show).containsKey(seat) && !seatLockMap.get(show).get(seat).isLockedExpired();
    }

    @Override
    public void unlockSeats(@NonNull final Show show, @NonNull final List<Seat> seats, @NonNull final String user) {

        for (Seat seat : seats) {
            validate(show, seat, user);
            unlockSeat(show, seat, user);
        }
    }

    private void unlockSeat(Show show, Seat seat, String user) {
        if (!seatLockMap.containsKey(show)) {
            return;
        }
        seatLockMap.get(show).remove(seat);
    }

    @Override
    public boolean validate(@NonNull final Show show, @NonNull final Seat seat, @NonNull final String user) {
        return seatLockMap.containsKey(show) && seatLockMap.get(show).containsKey(seat) &&
                seatLockMap.get(show).get(seat).getLockedBy().equals(user);
    }

    @Override
    public List<Seat> getLockedSeats(Show show) {
        if (!seatLockMap.containsKey(show)) {
            return ImmutableList.of();
        }
        List<Seat> lockedSeats = new ArrayList<>();
        for (Seat seat : seatLockMap.get(show).keySet()) {
            if (isSeatLocked(show, seat)) {
                lockedSeats.add(seat);
            }
        }
        return lockedSeats;
    }
}
