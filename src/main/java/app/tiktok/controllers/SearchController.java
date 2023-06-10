package app.tiktok.controllers;

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

    @GetMapping("/search")
    public String getSearchPage(@RequestParam String s, Model model){
        model.addAttribute("res", s);
        model.addAttribute("resVideos", videosRepository.findByNameVideo(s));
        return "search";
    }
}
