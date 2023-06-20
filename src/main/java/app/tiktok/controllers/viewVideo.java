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

import static app.tiktok.SHA256.getHashCode;

@Controller
public class viewVideo {
    @Autowired
    private UsersAccountRepository usersAccountRepository;
    @Autowired
    private LikesRepository likesRepository;
    @Autowired
    private VideosRepository videosRepository;
    @GetMapping("/@{userName}/video/{idVideo}")
    public String getAccountDetails(@PathVariable("userName") String userName, @PathVariable("idVideo") String idVideo, Model model) {
        Videos videoLikesAndNameVideo = videosRepository.findByUserNameAndCodeVideo(userName, idVideo);

        model.addAttribute("userName", userName);
        model.addAttribute("idVideo", idVideo);
        model.addAttribute("like", videoLikesAndNameVideo.getLikes());
        model.addAttribute("nameVideo", idVideo);
        model.addAttribute("photoId", usersAccountRepository.findByUserName(userName).getPhotoId());

        if (idVideo.endsWith(".mp4") || idVideo.endsWith(".avi")) {
            model.addAttribute("type", "video");
        } else if (idVideo.endsWith(".jpg") || idVideo.endsWith(".png")) {
            model.addAttribute("type", "img");
        }else{
            model.addAttribute("type", "img");
        }
        return "viewVideo";
    }

    @PostMapping("/@{userName}/video/{idVideo}")
    public String addLike(@RequestParam String login, @RequestParam String password, @PathVariable("userName") String userName, @PathVariable("idVideo") String idVideo, Model model){
        Videos videoLikesAndNameVideo = videosRepository.findByUserNameAndCodeVideo(userName, idVideo);
        UsersAccount user = usersAccountRepository.findByUserName(userName);

        if (idVideo.endsWith(".mp4") || idVideo.endsWith(".avi")) {
            model.addAttribute("type", "video");
        } else if (idVideo.endsWith(".jpg") || idVideo.endsWith(".png")) {
            model.addAttribute("type", "img");
        }else{
            model.addAttribute("type", "img");
        }
        model.addAttribute("userName", userName);
        model.addAttribute("idVideo", idVideo);
        model.addAttribute("like", videoLikesAndNameVideo.getLikes());
        model.addAttribute("nameVideo", videoLikesAndNameVideo.getNameVideo());
        model.addAttribute("photoId", user.getPhotoId());

        if (usersAccountRepository.findByLoginAndPasswordAndUserName(login, getHashCode(password), userName) != null){
            if (likesRepository.findByAccountIdAndNameAccountLikesVideoAndIdVideo(login, userName, idVideo) == null){
                model.addAttribute("message", "Successful");

                Likes addLike = new Likes(userName, idVideo, login, videoLikesAndNameVideo.getNameVideo());
                likesRepository.save(addLike);
                Videos video = videosRepository.findByUserNameAndCodeVideo(userName, idVideo);
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
