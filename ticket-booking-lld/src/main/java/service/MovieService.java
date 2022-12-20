package service;

import exceptions.NotFoundException;
import lombok.NonNull;
import model.Movie;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MovieService {

    private final Map<String, Movie> movieMap;

    public MovieService() {
        this.movieMap = new HashMap<>();
    }

    public Movie getMovie(@NonNull final String movieId) {
        if (!movieMap.containsKey(movieId)) {
            try {
                throw new NotFoundException("Movie is not present");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        return movieMap.get(movieId);
    }

    public Movie addMovie(@NonNull final String movieName) {
        String movieId = String.valueOf(UUID.randomUUID());
        Movie movie = new Movie(movieId, movieName);
        movieMap.put(movieId, movie);
        return movie;
    }

}
