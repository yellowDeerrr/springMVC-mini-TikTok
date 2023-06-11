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

import static app.tiktok.controllers.CreateAccountController.getString;

@Controller
public class ChangeAccountCotroller {
    @Autowired
    private UsersAccountRepository usersAccountRepository;
    private UsersAccount account;
    @GetMapping("/changeAccount")
    public String getPageChangeAccount(Model model){
        model.addAttribute("start", true);
        return "changeAccount/changeAccount";
    }

    @PostMapping("/changeAccount")
    public String changeAccount(@RequestParam String login, @RequestParam String password, Model model){
        UsersAccount userAccount = usersAccountRepository.findByLoginAndPassword(login, password);

        if (userAccount == null){
            model.addAttribute("start", true);
            model.addAttribute("errorMessage", "Error Login or Password");
        }else{
            model.addAttribute("start", false);
            model.addAttribute("accountName", login);
            account = userAccount;
        }

        return "changeAccount/changeAccount";
    }

    @GetMapping("/changeAccount/avatar")
    public String getPageAvatar(Model model){
        if (account == null){return "error";}

        model.addAttribute("accountName", account.getLogin());
        model.addAttribute("photoId", account.getPhotoId());
        return "changeAccount/avatar";
    }

    @PostMapping("/changeAccount/avatar")
    public String avatar(@RequestParam MultipartFile file, Model model) throws IOException {
        if (account == null){return "error";}

        String generate = generatePhotoId();
        byte[] bytes = file.getBytes();

        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null && !file.isEmpty()) {
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            String idVideoWithExtension = generate + "." + fileExtension;

            Path path = Paths.get("F:\\Java\\intellji\\tiktok\\src\\main\\resources\\templates\\images\\@" + account.getLogin() + "\\photo\\" + idVideoWithExtension);
            Files.createDirectories(path.getParent());
            Files.write(path, bytes);

            String accountId = account.getLogin();
            String oldPhotoId = account.getPhotoId();
            if (oldPhotoId != null) {
                Path oldFilePath = Paths.get("F:\\Java\\intellji\\tiktok\\src\\main\\resources\\templates\\images\\@" + accountId + "\\photo\\" + oldPhotoId);
                try {
                    Files.deleteIfExists(oldFilePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            account.setPhotoId(idVideoWithExtension);
            usersAccountRepository.save(account);
            model.addAttribute("accountName", account.getLogin());
            model.addAttribute("photoId", account.getPhotoId());
        }else{
            model.addAttribute("message", "Add photo");
        }
        return "changeAccount/avatar";
    }

    public String generatePhotoId(){
        return getString();
    }
    
    @GetMapping("/changeAccount/state")
    public String getPageState(Model model){
        if (account == null){return "error";}

        model.addAttribute("state", account.isCloseOrOpenAccount() ? "open" : "close");
        return "changeAccount/state";
    }

    @PostMapping("/changeAccount/state")
    public String state(@RequestParam String newState, Model model){
        if (account == null){return "error";}

        if (newState.equals("close")){
            account.setCloseOrOpenAccount(true);
        }else if (newState.equals("open")){
            account.setCloseOrOpenAccount(false);
        }else{
            model.addAttribute("message", "Choose");
        }

                usersAccountRepository.save(account);
        model.addAttribute("message", "Successful");
        model.addAttribute("state", newState);
        return "changeAccount/state";
    }
}
