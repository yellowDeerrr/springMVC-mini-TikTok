package app.tiktok.controllers;

import app.tiktok.repositores.LikesRepository;
import app.tiktok.repositores.UsersAccountRepository;
import app.tiktok.tables.Likes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

@Controller
public class MainPageController {
    @Autowired
    private LikesRepository likesRepository;
    @Autowired
    private UsersAccountRepository usersAccountRepository;
    @GetMapping("/")
    public String mainPage(){
        return "main";
    }

    @GetMapping("/@{accountName}/video/{idVideo}")
    public String getAccountDetails(@PathVariable("accountName") String accountName, @PathVariable("idVideo") String idVideo, Model model) {
        model.addAttribute("accountName", accountName);
        model.addAttribute("idVideo", idVideo);

        return "viewVideo"; // Приклад: Повернення назви представлення для відображення даних облікового запису
    }

    @PostMapping("/@{accountName}/video/{idVideo}")
    public String addLike(@RequestParam String login, @RequestParam String password, @PathVariable("accountName") String accountName, @PathVariable("idVideo") String idVideo, Model model){
        if (usersAccountRepository.findByLoginAndPassword(login, password) != null){
            if (likesRepository.findByAccountIdAndNameAccountLikesVideoAndIdVideo(login, accountName, idVideo) == null){
                Likes addLike = new Likes(accountName, idVideo, login);
                likesRepository.save(addLike);
                model.addAttribute("message", "Successful");
            }
            else{
                model.addAttribute("message", "You already liked this video");
            }
        }else{
            model.addAttribute("message", "Error Login or Password");
        }
        return "viewVideo";
    }

    @GetMapping("/@{accountName}")
    public String addLike(@PathVariable("accountName") String accountName, Model model){
        if(usersAccountRepository.findByLogin(accountName) != null){
            List<Likes> likes = likesRepository.findAllByAccountId(accountName);
            model.addAttribute("likes", likes);
        }else{
            model.addAttribute("errorMessage", "Account doesn't exist");
        }
        return "viewProfile";
    }
}
