package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Cinema {

    private int totalRows;
    private int totalColumns;

    private List<Seat> allSeats = new ArrayList<>();
    private List<Seat> bookedSeats = new ArrayList<>();

    public Cinema(int totalRows, int totalColumns) {

        this.totalRows = totalRows;
        this.totalColumns = totalColumns;

        for (int i = 1; i <= totalRows; i++) {
            for (int j = 1; j <= totalColumns; j++) {
                this.allSeats.add(new Seat(i, j));
            }
        }
    }

    @JsonProperty("total_rows")
    public int getTotalRows() {
        return totalRows;
    }

    @JsonProperty("total_columns")
    public int getTotalColumns() {
        return totalColumns;
    }

    @JsonProperty("available_seats")
    public List<Seat> getAvailableSeats() {
        List<Seat> availableSeats = new ArrayList<>(allSeats);
        availableSeats.removeAll(bookedSeats);
        return availableSeats;
    }

    public Seat bookSeat(Seat seat) throws IllegalArgumentException {
        if (bookedSeats.contains(seat)) {
            throw new IllegalArgumentException("The ticket has been already purchased!");
        }
        if (!allSeats.contains(seat)) {
            throw new IllegalArgumentException("The number of a row or a column is out of bounds!");
        }
        bookedSeats.add(seat);
        return seat;
    }
}
