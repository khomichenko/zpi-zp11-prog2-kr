package application.data;

import application.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AccountsRepository extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account> {

}
