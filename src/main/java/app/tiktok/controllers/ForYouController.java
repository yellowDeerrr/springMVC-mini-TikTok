package app.tiktok.controllers;

import app.tiktok.repositores.VideosRepository;
import app.tiktok.tables.Videos;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Random;

@Controller
public class ForYouController {
    @Autowired
    private VideosRepository videosRepository;

    @GetMapping("/foryou")
    public String getPageForYou(){
        long rowCount = videosRepository.count();

        Random random = new Random();

        int randomIndex = random.nextInt((int) rowCount);

        List<Videos> videoList = videosRepository.findAll();

        if (!videoList.isEmpty()) {
            Videos randomVideo = videoList.get(randomIndex);

            String nameAccount = randomVideo.getNameAccount();
            String codeVideo = randomVideo.getCodeVideo();

            // Виведення nameAccount і codeVideo
            System.out.println("Random Name Account: " + nameAccount);
            System.out.println("Random Video Code: " + codeVideo);
            return "redirect:/@" + nameAccount + "/video/" + codeVideo;
        } else {
            System.out.println("No videos found in the database.");
            return "main";
        }
    }
}
