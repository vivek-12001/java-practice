import com.google.common.collect.ImmutableList;
import exceptions.SeatNotAvailableException;
import exceptions.SeatTemporaryUnavailableException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

public class Case1Tests extends BaseTests {

    @BeforeEach
    void setup() {
        setupControllers(10, 0);
    }

    @Test
    void testCase1() {
        String user1 = "USER1";
        String user2 = "USER2";

        final String movie = movieController.createMovie("MOVIE 1");
        final String screen = setupScreen();
        final List<String> screen1SeatIds = createSeats(theatreController, screen, 5, 5);

        final String show = showController.createShow(movie, screen, new Date(), 2*60*60);

        List<String> availableSeats = showController.getAvailableSeats(show);

        validateSeatsList(availableSeats, screen1SeatIds, ImmutableList.of());

        ImmutableList<String> u1SeatsSelected = ImmutableList.of(
                screen1SeatIds.get(0),
                screen1SeatIds.get(2),
                screen1SeatIds.get(4),
                screen1SeatIds.get(6)
        );

        final String bookingId;
        try {
            bookingId = bookingController.createBooking(user1, show, u1SeatsSelected);
            paymentsController.paymentSuccess(user1, bookingId);
        } catch (SeatTemporaryUnavailableException e) {
            e.printStackTrace();
        } catch (SeatNotAvailableException e) {
            e.printStackTrace();
        }

        final List<String> u2AvailableSeats = showController.getAvailableSeats(show);

        validateSeatsList(u2AvailableSeats, screen1SeatIds, u1SeatsSelected);
    }
}
