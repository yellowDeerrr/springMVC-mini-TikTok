package app.tiktok.controllers;


import app.tiktok.repositores.UsersAccountRepository;
import app.tiktok.repositores.VideosRepository;
import app.tiktok.tables.UsersAccount;
import app.tiktok.tables.Videos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

import java.sql.Timestamp;
import java.util.Random;

import static app.tiktok.GenerateCodeForFile.getString;
import static app.tiktok.SHA256.getHashCode;


@Controller
public class CreateNewVideoController {
    @Autowired
    private UsersAccountRepository usersAccountRepository;
    @Autowired
    private VideosRepository videosRepository;

    @GetMapping("/createNewVideo")
    public String getPageCreateVideo(){
        return "createVideo";
    }

    @PostMapping("/createNewVideo")
    public String createVideo(@RequestParam String login, @RequestParam String password, @RequestParam String userName, @RequestParam String nameVideo, @RequestParam MultipartFile file, Model model){
        UsersAccount usersAccount = usersAccountRepository.findByLoginAndPasswordAndUserName(login, getHashCode(password), userName);
        if (usersAccount == null){
            model.addAttribute("message", "Error account");
        }
        else {
            String idVideo = getString();
            try {
                byte[] bytes = file.getBytes();

                String originalFilename = file.getOriginalFilename();
                if (originalFilename != null && !file.isEmpty()) {
                    String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
                    String idVideoWithExtension = idVideo + "." + fileExtension;

                    Path path = Paths.get("F:\\Java\\intellji\\spring\\projects\\tiktok\\src\\main\\resources\\templates\\images\\@" + userName + "\\video\\" + idVideoWithExtension);
                    Files.createDirectories(path.getParent());
                    Files.write(path, bytes);

                    Videos video = new Videos(login, idVideoWithExtension, usersAccount.getUserName(), new Timestamp(System.currentTimeMillis()), 0, nameVideo);
                    videosRepository.save(video);
                    model.addAttribute("message", "Successful");
                }else{
                    model.addAttribute("message", "Add photo");
                }
            } catch (IOException e) {
                model.addAttribute("message", "Unsuccessful");
            }
        }
        return "createVideo";
    }
}
