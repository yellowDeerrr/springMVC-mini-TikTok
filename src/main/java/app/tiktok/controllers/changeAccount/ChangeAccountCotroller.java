package app.tiktok.controllers.changeAccount;

import app.tiktok.SHA256;
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

import static app.tiktok.GenerateCodeForFile.createCodeForFile;
import static app.tiktok.SHA256.getHashCode;

@Controller
public class ChangeAccountCotroller {
    @Autowired
    protected UsersAccountRepository usersAccountRepository;
    protected UsersAccount account;
    @GetMapping("/changeAccount")
    public String getPageChangeAccount(Model model){
        if (account == null){
            model.addAttribute("start", true);
        }else{
            model.addAttribute("accountName", account.getLogin());
            model.addAttribute("start", false);
        }

        return "changeAccount/changeAccount";
    }

    @PostMapping("/changeAccount")
    public String changeAccount(@RequestParam String login, @RequestParam String password, @RequestParam String userName, Model model){
        UsersAccount userAccount = usersAccountRepository.findByLoginAndPasswordAndUserName(login, getHashCode(password), userName);

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

        String generate = createCodeForFile();
        byte[] bytes = file.getBytes();

        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null && !file.isEmpty()) {
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            String idVideoWithExtension = generate + "." + fileExtension;

            Path path = Paths.get("F:\\Java\\intellji\\spring\\projects\\tiktok\\src\\main\\resources\\templates\\images\\@" + account.getLogin() + "\\photo\\" + idVideoWithExtension);
            Files.createDirectories(path.getParent());
            Files.write(path, bytes);

            String accountId = account.getLogin();
            String oldPhotoId = account.getPhotoId();
            if (oldPhotoId != null) {
                Path oldFilePath = Paths.get("F:\\Java\\intellji\\spring\\projects\\tiktok\\src\\main\\resources\\templates\\images\\@" + accountId + "\\photo\\" + oldPhotoId);
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

    @GetMapping("/changeAccount/state")
    public String getPageState(Model model){
        if (account == null){return "error";}

        if (!account.isCloseOrOpenAccount()){
            model.addAttribute("state", "open");
        }else if(account.isCloseOrOpenAccount()){
            model.addAttribute("state", "close");
        }else{
            return "error";
        }
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

    @GetMapping("/changeAccount/login")
    public String getPageName(Model model){
        if (account == null){return "error";}

        model.addAttribute("name", account.getLogin());
        return "changeAccount/name";
    }

    @PostMapping("/changeAccount/login")
    public String name(@RequestParam String newName, Model model){
        if (account == null){return "error";}

        if (usersAccountRepository.findByLogin(newName) != null){
            model.addAttribute("name", account.getLogin());
            model.addAttribute("message", "Login is already using");
        }else{
            account.setLogin(newName);
            usersAccountRepository.save(account);
            model.addAttribute("name", newName);
            model.addAttribute("message", "Successful");
        }
        return "changeAccount/name";
    }

    @GetMapping("/changeAccount/password")
    public String getPagePassword(){
        if (account == null){return "error";}

        return "changeAccount/password";
    }

    @PostMapping("/changeAccount/password")
    public String password(@RequestParam String login, @RequestParam String password, @RequestParam String newPassword, Model model){
        if (account == null){return "error";}

        if (account.getLogin().equals(login) && account.getPassword().equals(getHashCode(password))){
            model.addAttribute("message", "Successful");

            account.setPassword(getHashCode(newPassword));
            usersAccountRepository.save(account);
        }else{
            model.addAttribute("message", "Unsuccessful");
        }

        return "changeAccount/password";
    }

    @GetMapping("/changeAccount/switch")
    public String switchAccount(){
        account = null;
        return "redirect:/changeAccount";
    }

    @PostMapping("/changeAccount/video")
    public String video(@RequestParam MultipartFile file, Model model) throws IOException {
        if (account == null){return "error";}

        String generate = createCodeForFile();
        byte[] bytes = file.getBytes();

        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null && !file.isEmpty()) {
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            String idVideoWithExtension = generate + "." + fileExtension;

            Path path = Paths.get("F:\\Java\\intellji\\spring\\projects\\tiktok\\src\\main\\resources\\templates\\images\\@" + account.getLogin() + "\\photo\\" + idVideoWithExtension);
            Files.createDirectories(path.getParent());
            Files.write(path, bytes);

            String accountId = account.getLogin();
            String oldPhotoId = account.getPhotoId();
            if (oldPhotoId != null) {
                Path oldFilePath = Paths.get("F:\\Java\\intellji\\spring\\projects\\tiktok\\src\\main\\resources\\templates\\images\\@" + accountId + "\\photo\\" + oldPhotoId);
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
}
