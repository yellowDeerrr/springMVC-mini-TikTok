package app.tiktok.controllers.viewProfile;

import app.tiktok.tables.Likes;
import app.tiktok.tables.UsersAccount;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class viewLikes extends viewProfileController{
    @GetMapping("/@{accountName}/likes")
    public String addLike(@PathVariable("accountName") String accountName, Model model){
        if(usersAccountRepository.findByLogin(accountName) != null){
            List<Likes> likes = likesRepository.findAllByAccountId(accountName);
            UsersAccount usersAccount = usersAccountRepository.findByLogin(accountName);
            model.addAttribute("accountId", accountName);
            model.addAttribute("photoId", usersAccount.getPhotoId());
            if (!usersAccount.isCloseOrOpenAccountLikes()){
                if (likes.isEmpty()){
                    model.addAttribute("errorMessage", "Account hasn't likes");
                }else{
                    model.addAttribute("likes", likes);
                }
            }else{
                model.addAttribute("errorMessage", "Account is close");
            }
        }else{
            model.addAttribute("errorMessage", "Account doesn't exist");
        }
        return "viewProfileLikes";
    }
}
