package application.controllers;

import application.services.AccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountsController {

    private final AccountsService accountsService;

    @GetMapping(path ="/list", produces = "application/json")
    public @ResponseBody Object list() {
        return accountsService.list();
    }

}
