import controller.*;
import model.Seat;
import org.junit.Assert;
import providers.SeatLockProviderImpl;
import service.*;

import java.util.ArrayList;
import java.util.List;

public class BaseTests {

    protected BookingController bookingController;
    protected MovieController movieController;
    protected ShowController showController;
    protected PaymentsController paymentsController;
    protected TheatreController theatreController;

    protected void setupControllers(int lockTimeout, int allowedRetries) {
        final SeatLockProviderImpl seatLockProvider = new SeatLockProviderImpl(lockTimeout);
        final BookingService bookingService = new BookingService(seatLockProvider);
        final MovieService movieService = new MovieService();
        final ShowService showService = new ShowService();
        final TheatreService theatreService = new TheatreService();
        final SeatAvailabilityService seatAvailabilityService
                = new SeatAvailabilityService(bookingService, seatLockProvider);
        final PaymentsService paymentsService = new PaymentsService(seatLockProvider, allowedRetries);

        bookingController = new BookingController(showService, bookingService, theatreService);
        showController = new ShowController(showService, theatreService, movieService, seatAvailabilityService);
        theatreController = new TheatreController(theatreService);
        movieController = new MovieController(movieService);
        paymentsController = new PaymentsController(paymentsService, bookingService);
    }

    public List<String> createSeats(TheatreController theatreController, String screenId, int numRows, int numSeatsInRow) {
        List<String> seats = new ArrayList<>();

        for (int rowNo = 0; rowNo < numRows; rowNo++) {
            for (int seatNo = 0; seatNo < numSeatsInRow; seatNo++) {
                String seat = theatreController.createSeatInScreen(rowNo, seatNo, screenId);
                seats.add(seat);
            }
        }
        return seats;
    }

    public void validateSeatsList(List<String> seatsList, List<String> allSeatsInScreen, List<String> excludedSeats) {
        for (String includedSeat : allSeatsInScreen) {
            if (!excludedSeats.contains(includedSeat)) {
                Assert.assertTrue(seatsList.contains(includedSeat));
            }
        }

        for (String excludedSeat : excludedSeats) {
            Assert.assertFalse(seatsList.contains(excludedSeat));
        }
    }

    public String setupScreen() {
        final String theatreId = theatreController.createTheatre("Theatre 1");
        return theatreController.createScreenInTheatre(theatreId, "Screen 1");
    }
}
