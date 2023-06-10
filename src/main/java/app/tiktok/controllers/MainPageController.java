package app.tiktok.controllers;

import app.tiktok.repositores.LikesRepository;
import app.tiktok.repositores.UsersAccountRepository;
import app.tiktok.repositores.VideosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {
    @Autowired
    private LikesRepository likesRepository;
    @Autowired
    private UsersAccountRepository usersAccountRepository;
    @Autowired
    private VideosRepository videosRepository;
    @GetMapping("/")
    public String mainPage(){
        return "main";
    }


}
