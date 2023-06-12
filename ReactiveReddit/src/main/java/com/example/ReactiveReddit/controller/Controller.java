package com.example.ReactiveReddit.controller;
import com.example.ReactiveReddit.entity.ChildrenData;
import com.example.ReactiveReddit.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@RestController
@RequestMapping("/subreddits")
public class Controller {

    @Autowired
    private Service service;

    @GetMapping("/r/{subreddit}")
    public void getSubReddits(@PathVariable String subreddit) throws Exception {
        service.getSubreddits(subreddit);
    }
    @GetMapping("/id/{id}")
    public Mono<ChildrenData> getPostById(@PathVariable String id){
        return  service.getPost(id);
    }
    @GetMapping("/author/{authorName}")
    public Flux<ChildrenData> findByAuthorName(@PathVariable String authorName){
        return  service.getPostByAuthorName(authorName);
    }
    @GetMapping("/text/{keyword}")
    public Flux<ChildrenData> findByTextRegex(@PathVariable String keyword){
        return service.getAllRedditsContaining(keyword);
    }
}
