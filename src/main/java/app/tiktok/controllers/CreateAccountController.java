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

import static app.tiktok.GenerateCodeForFile.createCodeForFile;
import static app.tiktok.SHA256.getHashCode;

@Controller
public class CreateAccountController {
    @Autowired
    private UsersAccountRepository usersAccountRepository;
    @GetMapping("/createAccount")
    public String getPageCreateAccount(){
        return "createAccount";
    }

    @PostMapping("/createAccount")
    public String createAccount(@RequestParam String login, @RequestParam String password, @RequestParam String userName, @RequestParam String operator, @RequestParam MultipartFile file, Model model) throws IOException {
        if (usersAccountRepository.findByLogin(login) == null){
            if (usersAccountRepository.findByUserName(userName) == null){

                String generate = createCodeForFile();
                if (usersAccountRepository.findByPhotoId(generate) == null){
                    byte[] bytes = file.getBytes();

                    String originalFilename = file.getOriginalFilename();
                    if (originalFilename != null && !file.isEmpty()) {
                        model.addAttribute("message", "Successful");

                        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
                        String idVideoWithExtension = generate + "." + fileExtension;

                        Path path = Paths.get("F:\\Java\\intellji\\spring\\projects\\tiktok\\src\\main\\resources\\templates\\images\\@" + userName + "\\photo\\" + idVideoWithExtension);
                        Files.createDirectories(path.getParent());
                        Files.write(path, bytes);

                        UsersAccount createUser = new UsersAccount(login, getHashCode(password), userName, new Timestamp(System.currentTimeMillis()), idVideoWithExtension, operator.equals("close"));
                        usersAccountRepository.save(createUser);
                    }else{
                        model.addAttribute("message", "Add photo");
                    }
                }else{
                    createAccount(login, password, userName, operator, file, model);
                }
            }else{
                model.addAttribute("message", "User name must be unique");
            }
        }else{
            model.addAttribute("message", "Login is already using");
        }
        return "createAccount";
    }
}
