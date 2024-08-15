package in.itkaran.bookmyshow_150824.repositories;

import in.itkaran.bookmyshow_150824.models.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheatreRepository extends JpaRepository<Theatre, Long> {
    in.itkaran.bookmyshow_150824.models.Theatre findByName(java.lang.String name);
}
