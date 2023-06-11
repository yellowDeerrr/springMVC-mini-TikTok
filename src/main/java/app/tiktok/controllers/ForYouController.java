package app.tiktok.controllers;

import app.tiktok.repositores.VideosRepository;
import app.tiktok.tables.Videos;
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
        if (rowCount <= 0){
            return "videoDoesntExist+";
        }

        else {
            Random random = new Random();

            int randomIndex = random.nextInt((int) rowCount);

            List<Videos> videoList = videosRepository.findAll();

            Videos randomVideo = videoList.get(randomIndex);

            String nameAccount = randomVideo.getNameAccount();
            String codeVideo = randomVideo.getCodeVideo();

            return "redirect:/@" + nameAccount + "/video/" + codeVideo;
        }
    }
}
