package app.tiktok.controllers;

import app.tiktok.repositores.UsersAccountRepository;
import app.tiktok.tables.UsersAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;

@Controller
public class CreateAccountController {
    @Autowired
    private UsersAccountRepository usersAccountRepository;
    @GetMapping("/CreateAccount")
    public String getPageCreateAccount(){
        return "createAccount";
    }

    @PostMapping("/CreateAccount")
    public String createAccount(@RequestParam String login, @RequestParam String password, Model model){
        UsersAccount usersAccount = usersAccountRepository.findByLogin(login);
        if (usersAccount == null){
            model.addAttribute("message", "Successful");
            UsersAccount createUser = new UsersAccount(login, password, new Timestamp(System.currentTimeMillis()));
            usersAccountRepository.save(createUser);
        }else{
            model.addAttribute("message", "Login is already using");
        }
        return "createAccount";
    }
}
