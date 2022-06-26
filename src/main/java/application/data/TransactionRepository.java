package application.data;

import application.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {

    @Query(value = "SELECT t FROM Transaction t WHERE t.id=?1", nativeQuery = false)
    List<Transaction> findByTransactionId(Long id);
}