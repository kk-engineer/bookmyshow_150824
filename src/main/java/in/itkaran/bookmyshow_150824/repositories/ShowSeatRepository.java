package in.itkaran.bookmyshow_150824.repositories;

import in.itkaran.bookmyshow_150824.models.ShowSeat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {
    @Override
    List<ShowSeat> findAllById(Iterable<Long> longs);

    @Override
    ShowSeat save(ShowSeat showSeat);
}