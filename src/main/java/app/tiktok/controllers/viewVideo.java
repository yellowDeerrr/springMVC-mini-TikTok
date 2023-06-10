package app.tiktok.controllers;

import app.tiktok.repositores.LikesRepository;
import app.tiktok.repositores.UsersAccountRepository;
import app.tiktok.repositores.VideosRepository;
import app.tiktok.tables.Likes;
import app.tiktok.tables.Videos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class viewVideo {
    @Autowired
    private UsersAccountRepository usersAccountRepository;
    @Autowired
    private LikesRepository likesRepository;
    @Autowired
    private VideosRepository videosRepository;
    @GetMapping("/@{accountName}/video/{idVideo}")
    public String getAccountDetails(@PathVariable("accountName") String accountName, @PathVariable("idVideo") String idVideo, Model model) {
        Videos videoLikesAndNameVideo = videosRepository.findByNameAccountAndCodeVideo(accountName, idVideo);

        model.addAttribute("accountName", accountName);
        model.addAttribute("idVideo", idVideo);
        model.addAttribute("like", videoLikesAndNameVideo.getLikes());
        model.addAttribute("nameVideo", videoLikesAndNameVideo.getNameVideo());
        model.addAttribute("photoId", usersAccountRepository.findByLogin(accountName).getPhotoId());

        if (idVideo.endsWith(".mp4") || idVideo.endsWith(".avi")) {
            model.addAttribute("type", "video");
        } else if (idVideo.endsWith(".jpg") || idVideo.endsWith(".png")) {
            model.addAttribute("type", "img");
        }else{
            model.addAttribute("type", "img");
        }
        return "viewVideo";
    }

    @PostMapping("/@{accountName}/video/{idVideo}")
    public String addLike(@RequestParam String login, @RequestParam String password, @PathVariable("accountName") String accountName, @PathVariable("idVideo") String idVideo, Model model){
        Videos videoLikesAndNameVideo = videosRepository.findByNameAccountAndCodeVideo(accountName, idVideo);

        if (idVideo.endsWith(".mp4") || idVideo.endsWith(".avi")) {
            model.addAttribute("type", "video");
        } else if (idVideo.endsWith(".jpg") || idVideo.endsWith(".png")) {
            model.addAttribute("type", "img");
        }else{
            model.addAttribute("type", "img");
        }
        model.addAttribute("accountName", accountName);
        model.addAttribute("idVideo", idVideo);
        model.addAttribute("like", videoLikesAndNameVideo.getLikes());
        model.addAttribute("nameVideo", videoLikesAndNameVideo.getNameVideo());
        model.addAttribute("photoId", usersAccountRepository.findByLogin(accountName).getPhotoId());

        if (usersAccountRepository.findByLoginAndPassword(login, password) != null){
            if (likesRepository.findByAccountIdAndNameAccountLikesVideoAndIdVideo(login, accountName, idVideo) == null){
                model.addAttribute("message", "Successful");

                Likes addLike = new Likes(accountName, idVideo, login, videoLikesAndNameVideo.getNameVideo());
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
}
