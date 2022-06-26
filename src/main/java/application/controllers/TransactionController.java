package application.controllers;

import application.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping(path ="/list", produces = "application/json")
    public @ResponseBody Object list() {
        return transactionService.list();
    }

}