package service;

import exceptions.BadRequestException;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import model.Booking;
import providers.SeatLockProvider;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class PaymentsService {

    Map<Booking, Integer> bookingFailures;
    private final SeatLockProvider seatLockProvider;
    private final Integer allowedRetries;

    public PaymentsService(SeatLockProvider seatLockProvider, Integer allowedRetries) {
        this.seatLockProvider = seatLockProvider;
        this.allowedRetries = allowedRetries;
        this.bookingFailures = new HashMap<>();
    }

    public void processFailedPayments(@NonNull final Booking booking, @NonNull final String user) {
        if (!booking.getUser().equals(user)) {
            try {
                throw new BadRequestException("user not found");
            } catch (BadRequestException e) {
                e.printStackTrace();
            }
        }
        if (!bookingFailures.containsKey(booking)) {
            bookingFailures.put(booking, 0);
        }
        final Integer currentFailuresCount = bookingFailures.get(booking);
        final Integer newFailureCount = currentFailuresCount + 1;
        bookingFailures.put(booking, newFailureCount);
        if (newFailureCount > allowedRetries) {
            seatLockProvider.unlockSeats(booking.getShow(), booking.getSeatsBooked(), booking.getUser());
        }
    }

}
