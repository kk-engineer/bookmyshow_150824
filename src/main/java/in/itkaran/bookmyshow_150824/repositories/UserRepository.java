package in.itkaran.bookmyshow_150824.repositories;

import in.itkaran.bookmyshow_150824.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    Optional<User> findById(Long userId);
    Optional<User> findByEmail(String email);
}
