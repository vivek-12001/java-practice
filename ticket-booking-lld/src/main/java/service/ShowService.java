package service;

import exceptions.NotFoundException;
import lombok.NonNull;
import model.Movie;
import model.Screen;
import model.Show;
import model.Theatre;

import java.util.*;

public class ShowService {

    private final Map<String, Show> showMap;

    public ShowService() {
        this.showMap = new HashMap<>();
    }

    public Show getShow(@NonNull final String showId) {
        if (!showMap.containsKey(showId)) {
            try {
                throw new NotFoundException("show is not present");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        return showMap.get(showId);
    }

    public Show createShow(@NonNull final Movie movie, @NonNull final Screen screen,
                           @NonNull final Date startTime, @NonNull final Integer durationInSeconds) {
        String showId = UUID.randomUUID().toString();
        Show show = new Show(showId, movie, screen, startTime, durationInSeconds);
        showMap.put(showId, show);
        return show;
    }

    public List<Show> getShowsForScreen(@NonNull final Screen screen) {
        List<Show> showList = new ArrayList<>();
        for (Show show : showMap.values()) {
            if (show.getScreen().getName().equals(screen.getName())) {
                showList.add(show);
            }
        }
        return showList;
    }

}
