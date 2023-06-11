package app.tiktok.controllers;

import app.tiktok.repositores.LikesRepository;
import app.tiktok.repositores.UsersAccountRepository;
import app.tiktok.tables.Likes;
import app.tiktok.tables.UsersAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class viewProfileController {
    @Autowired
    private UsersAccountRepository usersAccountRepository;
    @Autowired
    private LikesRepository likesRepository;
    @GetMapping("/@{accountName}")
    public String addLike(@PathVariable("accountName") String accountName, Model model){
        if(usersAccountRepository.findByLogin(accountName) != null){
            List<Likes> likes = likesRepository.findAllByAccountId(accountName);
            UsersAccount usersAccount = usersAccountRepository.findByLogin(accountName);
            model.addAttribute("accountId", accountName);
            model.addAttribute("photoId", usersAccount.getPhotoId());
            if (!usersAccount.isCloseOrOpenAccount()){
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
        return "viewProfile";
    }
}
