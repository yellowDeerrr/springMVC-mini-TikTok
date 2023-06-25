package app.tiktok.controllers;

import app.tiktok.repositores.CommentsRepository;
import app.tiktok.repositores.UsersAccountRepository;
import app.tiktok.tables.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static app.tiktok.SHA256.getHashCode;

@Controller
public class CommentsCotroller {
    @Autowired
    private CommentsRepository commentsRepository;
    @Autowired
    private UsersAccountRepository usersAccountRepository;

    @GetMapping("/@{accountName}/video/{idVideo}/comments")
    public String getPageCommnets(@PathVariable String accountName, @PathVariable String idVideo, Model model){
        model.addAttribute("accountNameWhichWriteComment", commentsRepository.findAllByAccountNameAndIdVideo(accountName, idVideo));

        return "comments";
    }

    @PostMapping("/@{accountName}/video/{idVideo}/comments")
    public String addComment(@PathVariable String accountName, @PathVariable String idVideo, @RequestParam String login, @RequestParam String password, @RequestParam String userName, @RequestParam String text, Model model){
        if (usersAccountRepository.findByLoginAndPasswordAndUserName(login, getHashCode(password), userName) != null){
            Comments comment = new Comments(accountName, idVideo, userName, text);
            commentsRepository.save(comment);

            model.addAttribute("message", "Successful");
        }else{
            model.addAttribute("message", "Unsuccessful");
        }

        model.addAttribute("accountNameWhichWriteComment", commentsRepository.findAllByAccountNameAndIdVideo(accountName, idVideo));

        return "comments";
    }
}
