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

// Створення об'єкту Random
        Random random = new Random();

// Генерація випадкового індексу
        int randomIndex = random.nextInt((int) rowCount);

// Отримання списку записів з бази даних
        List<Videos> videoList = videosRepository.findAll();

// Перевірка, чи список не порожній
        if (!videoList.isEmpty()) {
            // Отримання випадкового запису
            Videos randomVideo = videoList.get(randomIndex);

            // Отримання значень nameAccount і codeVideo з випадкового запису
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
