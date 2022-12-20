package controller;

import exceptions.NotFoundException;
import exceptions.SeatNotAvailableException;
import exceptions.SeatTemporaryUnavailableException;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import model.Booking;
import model.Seat;
import model.Show;
import service.BookingService;
import service.ShowService;
import service.TheatreService;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class BookingController {
    private final ShowService showService;
    private final BookingService bookingService;
    private final TheatreService theatreService;

    public String createBooking(@NonNull final String userId, @NonNull final String showId,
                                @NonNull final List<String> seatsIds) throws SeatTemporaryUnavailableException, SeatNotAvailableException {
        final Show show = showService.getShow(showId);
        final List<Seat> seats = seatsIds.stream().map(theatreService::getSeat).collect(Collectors.toList());
        return bookingService.createBooking(userId, show, seats).getId();
    }
}
