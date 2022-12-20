package service;

import exceptions.BadRequestException;
import exceptions.NotFoundException;
import exceptions.SeatNotAvailableException;
import exceptions.SeatTemporaryUnavailableException;
import lombok.NonNull;
import model.Booking;
import model.Seat;
import model.Show;
import providers.SeatLockProvider;

import java.util.*;
import java.util.stream.Collectors;

public class BookingService {

    private final Map<String, Booking> bookingMap;
    private final SeatLockProvider seatLockProvider;

    public BookingService(SeatLockProvider seatLockProvider) {
        this.seatLockProvider = seatLockProvider;
        this.bookingMap = new HashMap<>();
    }

    public Booking getBooking(@NonNull final String bookingId) {
        if (!bookingMap.containsKey(bookingId)) {
            try {
                throw new NotFoundException("Booking is not present");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        return bookingMap.get(bookingId);
    }

    public List<Booking> getAllBookings(@NonNull final Show show) {
        List<Booking> bookingList = new ArrayList<>();
        for (Booking booking : bookingMap.values()) {
            if (booking.getShow().equals(show)) {
                bookingList.add(booking);
            }
        }
        return bookingList;
    }

    public Booking createBooking(@NonNull final String userId, @NonNull final Show show, @NonNull final List<Seat> seats)
            throws SeatNotAvailableException, SeatTemporaryUnavailableException {
        if (anySeatAlreadyBooked(show, seats)) {
            throw new SeatNotAvailableException("seat is not available for booking");
        }
        seatLockProvider.lockSeats(show, seats, userId);
        String bookingId = UUID.randomUUID().toString();
        Booking booking = new Booking(bookingId, show, userId, seats);
        bookingMap.put(bookingId, booking);
        return booking;
    }

    private boolean anySeatAlreadyBooked(Show show, List<Seat> seat) {
        List<Seat> bookedSeats = getBookedSeats(show);
        for (Seat seat1 : seat) {
            if (bookedSeats.contains(seat1)) {
                return true;
            }
        }
        return false;
    }

    public List<Seat> getBookedSeats(Show show) {
        return getAllBookings(show).stream()
                .filter(Booking::isConfirmed)
                .map(Booking::getSeatsBooked)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public void confirmBooking(@NonNull final Booking booking, @NonNull final String user) {
        try {
            if (!booking.getUser().equals(user)) {
                throw new BadRequestException("user not found");
            }
        } catch (BadRequestException e) {
            e.printStackTrace();
        }

        for (Seat seat : booking.getSeatsBooked()) {
            try {
                if (!seatLockProvider.validate(booking.getShow(), seat, user)) {
                    throw new BadRequestException("bad request");
                }
            } catch (BadRequestException e) {
                e.printStackTrace();
            }
        }
        booking.confirmBooking();
    }
}
