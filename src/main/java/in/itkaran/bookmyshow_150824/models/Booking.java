package in.itkaran.bookmyshow_150824.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel {
    private String bookingReference;
    @ManyToMany
    private List<ShowSeat> showSeats;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;

    private int amount; // 20050 => 200.50

    @OneToMany
    private List<Payment> payments;

    public String toString() {
        return "Booking Details:\n" +
                "Reference: " + bookingReference + "\n" +
                "Movie: " + showSeats.get(0).getShow().getMovie().getName() + "\n" +
                "Show Time: " + showSeats.get(0).getShow().getStartTime() + "\n" +
                "Screen: " + showSeats.get(0).getShow().getScreen().getName() + "\n" +
                "Show Seats: " +  showSeats + "\n" +
                "User Name: " + user.getName() + "\n" +
                "Booking Status: " + bookingStatus + "\n" +
                "Amount: " + "Rs " + amount;
    }
}
