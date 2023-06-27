package kbaproject.clubsProject.core.dataAccess;

import kbaproject.clubsProject.core.entities.User;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User,Integer> {
    Optional<User> findByName(String username);
}
