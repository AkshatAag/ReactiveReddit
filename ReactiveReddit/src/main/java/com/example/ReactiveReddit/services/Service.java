package com.example.ReactiveReddit.services;

import com.example.ReactiveReddit.entity.ApiResponse;
import com.example.ReactiveReddit.entity.Children;
import com.example.ReactiveReddit.entity.ChildrenData;
import com.example.ReactiveReddit.repository.RedditRepo;
import com.example.ReactiveReddit.services.AuthAndFetch.FetchSubreddit;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private RedditRepo redditRepo;

    public void getSubreddits(String subreddit) throws JsonProcessingException {
        String apiResponse = FetchSubreddit.getSubredditsFromApi(subreddit);

        ObjectMapper objectMapper = new ObjectMapper();
        ApiResponse mappedApiResponse = objectMapper.readValue(apiResponse, ApiResponse.class);
        List<Children> childrenList = mappedApiResponse.getData().getChildren();
        List<ChildrenData> childrenDataList = childrenList.stream().map(Children::getData).toList();

        Flux<ChildrenData> childrenDataFlux=redditRepo.saveAll(childrenDataList);
        childrenDataFlux.subscribe();
        System.out.println("SAVED TO MONGO");

    }

    public Mono<ChildrenData> getPost(String id) {
        return redditRepo.findById(id);
    }

    public Flux<ChildrenData> getPostByAuthorName(String authorName) {
        return redditRepo.findByAuthorName(authorName);
    }

    public Flux<ChildrenData> getAllRedditsContaining(String keyword) {
        return redditRepo.findByTextRegex(keyword);
    }
}


