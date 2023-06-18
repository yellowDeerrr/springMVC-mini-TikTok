package app.tiktok.controllers;

import app.tiktok.repositores.UsersAccountRepository;
import app.tiktok.repositores.VideosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {
    @Autowired
    private VideosRepository videosRepository;
    @Autowired
    private UsersAccountRepository usersAccountRepository;

    @GetMapping("/search")
    public String getSearchPage(@RequestParam String request, Model model){
        model.addAttribute("res", request);
        model.addAttribute("resVideos", videosRepository.findByNameVideo(request));
        model.addAttribute("resAccounts", usersAccountRepository.findByUserName(request));

        return "search";
    }
}
