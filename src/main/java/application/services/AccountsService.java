package application.services;

import application.data.AccountsRepository;
import application.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountsService {

    private final AccountsRepository repo;

    public List<Account> list() {
        return repo.findAll();
    }

    public Account save(Account account) {
        return repo.saveAndFlush(account);
    }

    public void delete(List<Account> accounts) {
        for (Account account: accounts) {
            repo.delete(account);
        }
    }

}
