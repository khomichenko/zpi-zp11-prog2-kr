package application.services;

import application.data.TransactionRepository;
import application.exceptions.TransactionException;
import application.model.Account;
import application.model.Clause;
import application.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionService {

    private final TransactionRepository repo;
    private final AccountsService accountsService;

    public List<Transaction> list() {
        return repo.findAll();
    }

    public Transaction save(Transaction transaction) throws TransactionException {
        Account from = transaction.getFrom();
        Account to = transaction.getTo();
        Boolean toHas = false;
        for (Clause toC: to.getClauses()) {
            if (toC.equals(transaction.getClause())) {
                toHas = true;
            }
        }
        if (toHas) {
            Transaction res = repo.saveAndFlush(transaction);
            if (from!=null && from!=to) {
                from.setAmount(from.getAmount()- transaction.getAmount());
                accountsService.save(from);
            }
            to.setAmount(to.getAmount()+ transaction.getAmount());
            accountsService.save(to);
            return res;
        }
        throw new TransactionException();
    }
}
