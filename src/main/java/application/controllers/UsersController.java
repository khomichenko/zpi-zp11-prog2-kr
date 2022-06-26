package application.controllers;

import application.services.AccountsService;
import application.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UsersController {

    private final UsersService usersService;

    @GetMapping(path ="/list", produces = "application/json")
    public @ResponseBody Object list() {
        return usersService.list();
    }

}