package service;

import exceptions.NotFoundException;
import lombok.NonNull;
import model.Screen;
import model.Seat;
import model.Theatre;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TheatreService {

    private final Map<String, Theatre> theatreMap;
    private final Map<String, Screen> screenMap;
    private final Map<String, Seat> seatMap;

    public TheatreService() {
        this.theatreMap = new HashMap<>();
        this.screenMap = new HashMap<>();
        this.seatMap = new HashMap<>();
    }

    public Theatre getTheatre(@NonNull final String theatreId) {
        if (!theatreMap.containsKey(theatreId)) {
            try {
                throw new NotFoundException("Threate is not present");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        return theatreMap.get(theatreId);
    }

    public Screen getScreen(@NonNull final String screenId) {
        if (!screenMap.containsKey(screenId)) {
            try {
                throw new NotFoundException("Screen is not present");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        return screenMap.get(screenId);
    }
    public Seat getSeat(@NonNull final String seatId){
        if (!seatMap.containsKey(seatId)) {
            try {
                throw new NotFoundException("Seat is not present");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        return seatMap.get(seatId);
    }

    public Theatre addTheatre(@NonNull final String theatreName) {
        String theatreId = UUID.randomUUID().toString();
        Theatre theatre = new Theatre(theatreId, theatreName);
        theatreMap.put(theatreId, theatre);
        return theatre;
    }

    public Screen createScreenInTheatre(@NonNull final String screenName, @NonNull final Theatre theatre) {
        Screen screen = createScreen(screenName, theatre);
        theatre.addScreen(screen);
        return screen;
    }

    private Screen createScreen(String screenName, Theatre theatre) {
        String screenId = UUID.randomUUID().toString();
        Screen screen = new Screen(screenId, screenName, theatre);
        screenMap.put(screenId, screen);
        return screen;
    }

    public Seat createSeatInScreen(@NonNull final Integer seatNo, @NonNull final Integer rowNo, @NonNull final Screen screen) {
        String seatId = UUID.randomUUID().toString();
        Seat seat = new Seat(seatId, rowNo, seatNo);
        seatMap.put(seatId, seat);
        screen.addSeat(seat);
        return seat;
    }
}
