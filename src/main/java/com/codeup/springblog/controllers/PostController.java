package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {
    @GetMapping("/posts")
    @ResponseBody
    public String getAds(){
        return "posts index page";
    }
    @GetMapping("/posts/{id}")
    @ResponseBody
    public String getAds(@PathVariable long id){
        return "here is your ad for id " + id;
    }
    @GetMapping("/posts/create")
    @ResponseBody
    public String viewCreatePost(){
        return "<form><input type=\"input\" name=\"post\"method=\"post\"/></form>";
    }
    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost(){
        return "We are posting!";
    }
}
