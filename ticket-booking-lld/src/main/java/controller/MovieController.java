package controller;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import service.MovieService;

@AllArgsConstructor
public class MovieController {

    private final MovieService movieService;

    public String createMovie(@NonNull final String movieName) {
        return movieService.addMovie(movieName).getId();
    }
}
