package model;

import exceptions.InvalidStateException;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;
/*

@Getter
public class Booking {

    private final String id;
    private final Show show;
    private final List<Seat> seatsBooked;
    private final String user;
    private BookingStatus bookingStatus;

    public Booking(@NonNull final String id, @NonNull final Show show, @NonNull final List<Seat> seatsBooked, @NonNull final String user) {
        this.id = id;
        this.show = show;
        this.seatsBooked = seatsBooked;
        this.user = user;
        this.bookingStatus = BookingStatus.Created;
    }

    public Boolean isConfirmed() {
        return this.bookingStatus == BookingStatus.Confirmed;
    }

    public void confirmBooking() {
        try {
            if (this.bookingStatus != BookingStatus.Created) {
                throw new InvalidStateException("Booking status is not Created, So it cannot be confirmed");
            }
        } catch (InvalidStateException e) {
            e.printStackTrace();
        }
        this.bookingStatus = BookingStatus.Confirmed;
    }

    public void expiredBooking() {
        try {
            if (this.bookingStatus != BookingStatus.Created) {
                throw new InvalidStateException("Booking status is not Created, So it cannot be confirmed");
            }
        } catch (InvalidStateException e) {
            e.printStackTrace();
        }
        this.bookingStatus = BookingStatus.Expired;
    }
}
*/

@Getter
public class Booking {

    private final String id;
    private final Show show;
    private final List<Seat> seatsBooked;
    private final String user;
    private BookingStatus bookingStatus;

    public Booking(@NonNull final String id, @NonNull final Show show, @NonNull final String user,
                   @NonNull final List<Seat> seatsBooked) {
        this.id = id;
        this.show = show;
        this.seatsBooked = seatsBooked;
        this.user = user;
        this.bookingStatus = BookingStatus.Created;
    }

    public boolean isConfirmed() {
        return this.bookingStatus == BookingStatus.Confirmed;
    }

    public void confirmBooking() {
        if (this.bookingStatus != BookingStatus.Created) {
            try {
                throw new InvalidStateException("Booking status is not Created, So it cannot be confirmed");
            } catch (InvalidStateException e) {
                e.printStackTrace();
            }
        }
        this.bookingStatus = BookingStatus.Confirmed;
    }

    public void expireBooking() {
        if (this.bookingStatus != BookingStatus.Created) {
            try {
                throw new InvalidStateException("Booking status is not Created, So it cannot be confirmed");
            } catch (InvalidStateException e) {
                e.printStackTrace();
            }
        }
        this.bookingStatus = BookingStatus.Expired;
    }
}