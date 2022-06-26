package application.data;

import application.model.Clause;
import application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClauseRepository extends JpaRepository<Clause, Long>, JpaSpecificationExecutor<User> {

    @Query(value = "SELECT c FROM Clause c WHERE c.id=?1", nativeQuery = false)
    List<Clause> findByClauseId(Long id);
}