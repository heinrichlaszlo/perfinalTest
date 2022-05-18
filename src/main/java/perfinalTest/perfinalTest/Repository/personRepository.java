package perfinalTest.perfinalTest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import perfinalTest.perfinalTest.entity.person;

public interface personRepository extends JpaRepository<person, String> {
}
