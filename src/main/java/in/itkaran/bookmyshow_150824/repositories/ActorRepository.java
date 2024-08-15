package in.itkaran.bookmyshow_150824.repositories;

import in.itkaran.bookmyshow_150824.models.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    Actor findByName(String name);
}
