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


@Controller
public class CreateNewVideo {
    @Autowired
    private UsersAccountRepository usersAccountRepository;
    @Autowired
    private VideosRepository videosRepository;

    @GetMapping("/createNewVideo")
    public String getPageCreateVideo(){
        return "createVideo";
    }

    @PostMapping("/createNewVideo")
    public String createVideo(@RequestParam String login, @RequestParam String password, @RequestParam MultipartFile file, Model model){
        UsersAccount usersAccount = usersAccountRepository.findByLoginAndPassword(login, password);
        if (usersAccount == null){
            model.addAttribute("message", "Error account");
        }
        else {
            String idVideo = createCodeVideoAndJoinToDb();
            try {
                byte[] bytes = file.getBytes();

                String originalFilename = file.getOriginalFilename();
                if (originalFilename != null) {
                    String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
                    String idVideoWithExtension = idVideo + "." + fileExtension;

                    String imagePath = "http://localhost:8080/" + usersAccount.getLogin() + "/" + idVideoWithExtension;
                    Path path = Paths.get("C:\\Users\\I\\Pictures\\tiktok\\@" + usersAccount.getLogin() + "\\video\\" + idVideoWithExtension);
                    Files.createDirectories(path.getParent());
                    Files.write(path, bytes);
                }

                Videos video = new Videos(login, idVideo, new Timestamp(System.currentTimeMillis()), 0);
                videosRepository.save(video);
                model.addAttribute("message", "Successful");
            } catch (IOException e) {
                model.addAttribute("message", "Unsuccessful");
            }
        }
        return "createVideo";
    }



    private String createCodeVideoAndJoinToDb(){
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            char randomSymbol = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".charAt(random.nextInt("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".length())); // Випадковий символ зі стрічки symbols

            stringBuilder.append(randomSymbol);
        }

        return stringBuilder.toString();
    }
}
