package application.services;

import application.data.UsersRepository;
import application.model.Account;
import application.model.Clause;
import application.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UsersService {
    private final UsersRepository repo;

    private final AccountsService accountsService;

    public List<User> list() {
        return repo.findAll();
    }

    public Boolean has(String username) {
        return repo.findByUserName(username).isEmpty()==false;
    }

    public User get(String username) {
        for (User user: repo.findByUserName(username)) {
            return user;
        }
        return null;
    }

    public User save(User user) {
        return repo.saveAndFlush(user);
    }

    public void clearAccounts(User user) {
        List<Account> toDelete = new ArrayList<>();
        for (Account account: accountsService.list()){
            if (account.getUser().equals(user)) {
                toDelete.add(account);
            }
        }
        accountsService.delete(toDelete);
    }
    public Account newAccount(User user, List<Clause> clauses) {
        Account account = new Account();
        account.setUser(user);
        account.setClauses(clauses);
        account.setAmount(0.0d);
        return accountsService.save(account);
    }
}
