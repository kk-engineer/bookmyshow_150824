package in.itkaran.bookmyshow_150824.repositories;

import in.itkaran.bookmyshow_150824.models.Screen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreenRepository extends JpaRepository<Screen, Long> {
    Screen findByName(String name);
}
