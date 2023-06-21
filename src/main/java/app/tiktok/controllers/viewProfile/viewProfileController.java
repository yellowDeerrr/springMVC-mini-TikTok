package app.tiktok.controllers.viewProfile;

import app.tiktok.repositores.LikesRepository;
import app.tiktok.repositores.UsersAccountRepository;
import app.tiktok.repositores.VideosRepository;
import app.tiktok.tables.UsersAccount;
import app.tiktok.tables.Videos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class viewProfileController {
    @Autowired
    protected UsersAccountRepository usersAccountRepository;
    @Autowired
    protected LikesRepository likesRepository;
    @Autowired
    protected VideosRepository videosRepository;

//  view videos user's
    @GetMapping("/@{userName}")
    public String addLike(@PathVariable("userName") String userName, Model model){
        if(usersAccountRepository.findByUserName(userName) != null){
            List<Videos> videos = videosRepository.findByUserName(userName);
            UsersAccount usersAccount = usersAccountRepository.findByUserName(userName);

            model.addAttribute("userName", userName);
            model.addAttribute("photoId", usersAccount.getPhotoId());
            if (!usersAccount.isCloseOrOpenAccountVideo()){
                if (videos.isEmpty()){
                    model.addAttribute("errorMessage", "Account hasn't videos");
                }else{
                    model.addAttribute("videos", videos);
                }
            }else{
                model.addAttribute("errorMessage", "Account is close");
            }
        }else{
            model.addAttribute("errorMessage", "Account doesn't exist");
        }
        return "viewProfile";
    }
}
