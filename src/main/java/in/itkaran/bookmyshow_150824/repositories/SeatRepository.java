package in.itkaran.bookmyshow_150824.repositories;

import in.itkaran.bookmyshow_150824.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    //Seat findBySeatId(Long seatId);
}
