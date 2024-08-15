package in.itkaran.bookmyshow_150824.repositories;

import in.itkaran.bookmyshow_150824.models.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
    City findByName(String name);
}
