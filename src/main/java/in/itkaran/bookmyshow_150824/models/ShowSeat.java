package in.itkaran.bookmyshow_150824.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShowSeat extends BaseModel {
    @ManyToOne
    private Show show;
    @ManyToOne
    private Seat seat;
    @Enumerated(EnumType.ORDINAL)
    private ShowSeatStatus showSeatStatus;

    public String toString() {
        return "\nSeat: " + seat.getNumber() + "\n" +
                "Status: " + showSeatStatus;
    }
}
