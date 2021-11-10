package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {
    private final PostRepository postDao;
    private final UserRepository userDao;
    private final EmailService emailService;

    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService){
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }
    @GetMapping("/posts")
    public String getAds(Model model){
        model.addAttribute("posts", postDao.findAll());
        model.addAttribute("user", userDao.getById(1L));
        return "posts/index";
    }
    @GetMapping("/posts/{id}")
    public String getAd(@PathVariable long id, Model model){
        model.addAttribute("post", postDao.getById(id));
        model.addAttribute("user", userDao.getById(1L));
        return "posts/show";
    }

    @GetMapping(value = "/posts/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public String createPost(Model model){
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post newPost){
        newPost.setUser(userDao.getById(1L));
        postDao.save(newPost);
        emailService.prepareAndSend(newPost, "You created a post!", "Here's a test body");
//        return "Ad created with an ID of: " + newPost.getId() +
//                " and with the user id of " + newPost.getUser().getId();
        return "redirect:/posts";
    }
    @GetMapping("/posts/{id}/edit")
    public String returnEditView(@PathVariable long id, Model model){
        model.addAttribute("post", postDao.getById(id));
        return "posts/edit";
    }
    @PostMapping("/posts/{id}/edit")
    public String updatePost(@ModelAttribute Post edittedPost){
        postDao.save(edittedPost);
        return "redirect:/posts";
    }
    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id){
        postDao.deleteById(id);
        return "redirect:/posts";
    }
}
