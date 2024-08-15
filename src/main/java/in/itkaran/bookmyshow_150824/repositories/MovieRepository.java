package in.itkaran.bookmyshow_150824.repositories;

import in.itkaran.bookmyshow_150824.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findByName(String name);
}
