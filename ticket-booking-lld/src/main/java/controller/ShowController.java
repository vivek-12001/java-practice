package controller;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import model.Movie;
import model.Screen;
import model.Seat;
import model.Show;
import service.MovieService;
import service.SeatAvailabilityService;
import service.ShowService;
import service.TheatreService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ShowController {

    private final ShowService showService;
    private final TheatreService theatreService;
    private final MovieService movieService;
    private final SeatAvailabilityService seatAvailabilityService;

    public String createShow(@NonNull final String movieId, @NonNull final String screenId, @NonNull final Date startTime,
                             @NonNull final Integer durationInSeconds) {
        final Screen screen = theatreService.getScreen(screenId);
        final Movie movie = movieService.getMovie(movieId);
        return showService.createShow(movie, screen, startTime, durationInSeconds).getId();
    }

    public List<String> getAvailableSeats(@NonNull final String showId) {
        final Show show = showService.getShow(showId);
        final List<Seat> availableSeats = seatAvailabilityService.getAvailableSeats(show);
        return availableSeats.stream().map(Seat::getId).collect(Collectors.toList());
    }
}
