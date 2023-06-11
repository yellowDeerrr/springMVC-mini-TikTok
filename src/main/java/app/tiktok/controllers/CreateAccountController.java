package app.tiktok.controllers;

import app.tiktok.repositores.UsersAccountRepository;
import app.tiktok.tables.UsersAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Random;

@Controller
public class CreateAccountController {
    @Autowired
    private UsersAccountRepository usersAccountRepository;
    @GetMapping("/createAccount")
    public String getPageCreateAccount(){
        return "createAccount";
    }

    @PostMapping("/createAccount")
    public String createAccount(@RequestParam String login, @RequestParam String password, @RequestParam String operator, @RequestParam MultipartFile file, Model model) throws IOException {
        UsersAccount usersAccount = usersAccountRepository.findByLogin(login);
        if (usersAccount == null){
            String generate = generatePhotoId();
            if (usersAccountRepository.findByPhotoId(generate) == null){
                model.addAttribute("message", "Successful");
                byte[] bytes = file.getBytes();

                String originalFilename = file.getOriginalFilename();
                if (originalFilename != null && !file.isEmpty()) {
                    String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
                    String idVideoWithExtension = generate + "." + fileExtension;

                    Path path = Paths.get("F:\\Java\\intellji\\tiktok\\src\\main\\resources\\templates\\images\\@" + login + "\\photo\\" + idVideoWithExtension);
                    Files.createDirectories(path.getParent());
                    Files.write(path, bytes);
                    UsersAccount createUser = new UsersAccount(login, password, new Timestamp(System.currentTimeMillis()), idVideoWithExtension, operator.equals("close"));
                    usersAccountRepository.save(createUser);
                }else{
                    model.addAttribute("message", "Add photo");
                }
            }else{
                createAccount(login, password, operator, file, model);
            }

        }else{
            model.addAttribute("message", "Login is already using");
        }
        return "createAccount";
    }

    public String generatePhotoId(){
        return getString();
    }
    static String getString() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 20; i++) {
            char randomSymbol = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".charAt(random.nextInt("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".length())); // Випадковий символ зі стрічки symbols

            stringBuilder.append(randomSymbol);
        }

        return stringBuilder.toString();
    }
}
