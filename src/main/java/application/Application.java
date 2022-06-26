package application;

import application.exceptions.TransactionException;
import application.model.Account;
import application.model.Clause;
import application.model.Transaction;
import application.model.User;
import application.services.AccountsService;
import application.services.ClausesService;
import application.services.TransactionService;
import application.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired public Application(
            UsersService usersService,
            ClausesService clausesService,
            AccountsService accountsService,
            TransactionService transactionService) {
        if (usersService.has("admin")==false) {
            usersService.save(new User("admin","Хоміченко Олександр Олексійович"));
        }
        if (usersService.has("user_01")==false) {
            usersService.save(new User("user_01","Петренко Василь Іванович"));
        }
        if (usersService.has("user_02")==false) {
            usersService.save(new User("user_02","Яковчук Сергій Петрович"));
        }
        Clause clauseTopUp = clausesService.has(1L)==false ? clausesService.save(new Clause(1L,"Поповнення")) : clausesService.get(1L);
        Clause clauseSalary= clausesService.has(2L)==false ? clausesService.save(new Clause(2L,"Заробітня плата")) : clausesService.get(2L);
        Clause clauseTax   = clausesService.has(3L)==false ? clausesService.save(new Clause(3L,"Податок")) : clausesService.get(3L);
        Clause clauseInsurance = clausesService.has(4L)==false ? clausesService.save(new Clause(4L,"Страхування")) : clausesService.get(4L);
        Clause clauseCommunal = clausesService.has(5L)==false ? clausesService.save(new Clause(5L,"Комуналка")) : clausesService.get(5L);

        usersService.clearAccounts(usersService.get("user_01"));
        Account account0101 = usersService.newAccount(   usersService.get("user_01"),clausesService.get(1L,2L,3L,4L,5L));
        Account account0102 = usersService.newAccount(   usersService.get("user_01"),clausesService.get(1L,2L,5L));

        usersService.clearAccounts(usersService.get("user_02"));
        Account account0201 = usersService.newAccount(   usersService.get("user_02"),clausesService.get(1L,2L,3L,4L,5L));
        Account account0202 = usersService.newAccount(   usersService.get("user_02"),clausesService.get(1L,2L,3L,5L));

        Transaction trans01 = new Transaction(account0101, account0101, clauseTopUp, 100d);
        try {
            transactionService.save(trans01);
        } catch (TransactionException e) {
            System.out.println("Погана трансакція!");
        }

        Transaction trans02 = new Transaction(account0201, account0201, clauseTopUp, 200d);
        try {
            transactionService.save(trans02);
        } catch (TransactionException e) {
            System.out.println("Погана трансакція!");
        }

        Transaction trans03 = new Transaction(account0101, account0201, clauseSalary, 7800d);
        try {
            transactionService.save(trans03);
        } catch (TransactionException e) {
            System.out.println("Погана трансакція!");
        }

        Transaction trans04 = new Transaction(account0101, account0201, clauseCommunal, -1240d);
        try {
            transactionService.save(trans04);
        } catch (TransactionException e) {
            System.out.println("Погана трансакція!");
        }

        Transaction trans05 = new Transaction(account0101, account0202, clauseTax, -260d);
        try {
            transactionService.save(trans05);
        } catch (TransactionException e) {
            System.out.println("Погана трансакція!");
        }

        System.out.println(account0101.getAmount());
    }


}
