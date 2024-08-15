package in.itkaran.bookmyshow_150824.repositories;

import in.itkaran.bookmyshow_150824.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    //Booking findByBookingId(Long bookingId);
}
