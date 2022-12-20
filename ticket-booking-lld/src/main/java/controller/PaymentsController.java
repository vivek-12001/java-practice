package controller;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import model.Booking;
import service.BookingService;
import service.PaymentsService;

@AllArgsConstructor
public class PaymentsController {

    private final PaymentsService paymentsService;
    private final BookingService bookingService;

    public void paymentFailed(@NonNull final String userId, @NonNull final String bookingId) {
        paymentsService.processFailedPayments(bookingService.getBooking(bookingId), userId);
    }

    public void paymentSuccess(@NonNull final String userId, @NonNull final String bookingId) {
        bookingService.confirmBooking(bookingService.getBooking(bookingId), userId);
    }
}
