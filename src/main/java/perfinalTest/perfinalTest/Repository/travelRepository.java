package perfinalTest.perfinalTest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import perfinalTest.perfinalTest.entity.travel;
import org.springframework.data.repository.query.Param;

import java.sql.Date;

@Repository
@EnableJpaRepositories
public interface travelRepository extends JpaRepository<travel, Long> {

    @Query ("SELECT t.city.id FROM travel t WHERE t.travelDate = (Select max(ttt.travelDate) FROM travel ttt WHERE ttt.person.name= :name) ")
    public Long getMaxDateId(@Param("name") String name);

    @Query ("SELECT t.city.id FROM travel t WHERE t.travelDate = (SELECT max(t.travelDate) FROM travel t WHERE t.travelDate not in(SELECT max(tt.travelDate) FROM travel tt WHERE tt.person.name= :name))")
    public Long getSecondMaxDateId(@Param("name") String name);
}
