package in.itkaran.bookmyshow_150824.repositories;

import in.itkaran.bookmyshow_150824.models.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatTypeRepository extends JpaRepository<SeatType, Long> {
    SeatType findByName(String name);
}
