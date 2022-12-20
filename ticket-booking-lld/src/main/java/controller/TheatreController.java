package controller;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import model.Screen;
import model.Theatre;
import service.TheatreService;

@AllArgsConstructor
public class TheatreController {

    private final TheatreService theatreService;

    public String createTheatre(@NonNull final String theatreName) {
        return theatreService.addTheatre(theatreName).getId();
    }

    public String createScreenInTheatre(@NonNull final String theatreId, @NonNull final String screenName) {
        final Theatre theatre = theatreService.getTheatre(theatreId);
        return theatreService.createScreenInTheatre(screenName, theatre).getId();
    }

    public String createSeatInScreen(@NonNull final Integer rowNo, @NonNull final Integer seatNo, @NonNull final String screenId) {
        final Screen screen = theatreService.getScreen(screenId);
        return theatreService.createSeatInScreen(seatNo, rowNo, screen).getId();
    }
}
