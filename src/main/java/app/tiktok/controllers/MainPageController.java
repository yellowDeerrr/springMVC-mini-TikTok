package app.tiktok.controllers;

import app.tiktok.repositores.LikesRepository;
import app.tiktok.repositores.UsersAccountRepository;
import app.tiktok.repositores.VideosRepository;
import app.tiktok.tables.Likes;
import app.tiktok.tables.UsersAccount;
import app.tiktok.tables.Videos;
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
    @Autowired
    private VideosRepository videosRepository;
    @GetMapping("/")
    public String mainPage(){
        return "main";
    }

    @GetMapping("/@{accountName}/video/{idVideo}")
    public String getAccountDetails(@PathVariable("accountName") String accountName, @PathVariable("idVideo") String idVideo, Model model) {
        model.addAttribute("accountName", accountName);
        model.addAttribute("idVideo", idVideo);
        model.addAttribute("like", videosRepository.findByNameAccountAndCodeVideo(accountName, idVideo).getLikes());

        return "viewVideo"; // Приклад: Повернення назви представлення для відображення даних облікового запису
    }

    @PostMapping("/@{accountName}/video/{idVideo}")
    public String addLike(@RequestParam String login, @RequestParam String password, @PathVariable("accountName") String accountName, @PathVariable("idVideo") String idVideo, Model model){
        model.addAttribute("like", videosRepository.findByNameAccountAndCodeVideo(accountName, idVideo).getLikes());
        if (usersAccountRepository.findByLoginAndPassword(login, password) != null){
            if (likesRepository.findByAccountIdAndNameAccountLikesVideoAndIdVideo(login, accountName, idVideo) == null){
                model.addAttribute("message", "Successful");
                Likes addLike = new Likes(accountName, idVideo, login);
                likesRepository.save(addLike);
                Videos video = videosRepository.findByNameAccountAndCodeVideo(accountName, idVideo);
                video.setLikes(video.getLikes() + 1);
                videosRepository.save(video);
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
            UsersAccount usersAccount = usersAccountRepository.findByLogin(accountName);
            model.addAttribute("likes", likes);
            model.addAttribute("accountId", accountName);
            model.addAttribute("photoId", usersAccount.getPhotoId());
            if (likes == null){
                model.addAttribute("errorMessage", "Account hasn't likes");
            }
        }else{
            model.addAttribute("errorMessage", "Account doesn't exist");
        }
        return "viewProfile";
    }
}
